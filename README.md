# ![JAWN](http://i.imgur.com/T6KrwH8.png)

I wanted weather notifications on my Fitbit Blaze. Since I could not find an app that did this I made my own. Turns out there was a lot to learn making this simple app and I am providing the code hoping this will help others as well as get some needed criticism (be kind).

## Motivation

The Fitbit Blaze is not a true "smartwatch", at least not in the way same way a Pebble, iWatch or Android watch is a "smartwatch". There is no "app store" to provide a bevy of customizable apps. Instead you essentially have a fatter LED Fitbit with a few nice extras. If there is a feature you would like from the Blaze, such as a Weather Forecast, you pretty much have to hope that the Fitbit company decides to add it.

Personally I wanted to know what the weather was going to be when I go to work and when I leave work and for convenience I wanted it to be on my Blaze. I also know that other Blaze owners would like convenience as well:

https://community.fitbit.com/t5/Feature-Suggestions/Blaze-needs-weather-app/idi-p/1222066?nobounce

There are 130 comments and 1,281 votes for this feature request. Which means if I somehow add a Weather Forecast to the Blaze it has a ready audience.

## Method

Well one thing that the Blaze has that its slimmer siblings do not is Notifications from your phone.

![Blaze Notification](http://i.imgur.com/8LLtV57.jpg "Blaze Notification")

Notifications on the Blaze appear almost exactly the same as on your phone. You get an icon representing the app that sent the notification, a title (in this case the person sending you text), and the actual text of the notification. Notifications on the Blaze will also stay in a list you can bring up with a simple swipe until you choose to clear the whole list.

So that gives us a title and potentially enough space for a forecast text. But what if I really want to optimize my use of the screen?

## A 🖼️ is 💰 a 1,000 🗣️

By now we all know what emojis are, but some people may not be aware that many of the emojis you get on your phone are actually displayed the same way text is. For example, this smiley face emoji 😊  is just a character value from Unicode and depending on what device and browser/app you view it on can actually look very different. It may not even appear correctly at all if your device/browser/app has yet to support that particular emoji. The classic grinning face emoji 😀 was part of emoji version 1.0 but this Santa emoji 🎅🏿 was part of version 2.0. So what emojis work depends on your device/browser/app.

So to make optimum use of textual space in the Notifications I decided to use a single character emoji to represent different weather conditions like rain, thunder storm, snow, sunny, etc saving us a lot of space.

But what emojis can we use for weather? After all 😀 is not going to help us. Turns out there are a lot other emojis that could. There are emojis for sun, cloud, cloud with rain, cloud with snow, a snowflake, cloud with thunder.

So we have a lot of emojis but will they all work on the Blaze?

## A Quick Test

I already knew that the U.S. version of the Blaze did not display international text, so I was not sure if emojis would work.

To check I built a simple app to display some emojis. Here is a screenshot of it with some weather emojis. Next to it are how the emojis appeared on the Blaze when sent as notifications.

![test emojis](http://i.imgur.com/5zAYaG5.png "test emojis")

So the emojis "sun", "cloud", "sun behind cloud", "stormy cloud", "crescent moon", "city night sky", "city dusk sky", "milky way" and oddly enough, "snowman" work. But the emojis "snowflake", "cloud with rain", "cloud with snow", and "sun behind rainy cloud" do not work, in the picture you can only see these unrecognized emojis as dotted squares. Since those emojis were not going to work I ended up using an "umbrella" emoji for rain and the "snowman" for snow.

If you're curious the reason I needed an emoji like "night sky" or "crescent moon" was to help me represent a "clear" forecast during the night since the sun emoji is fine for daytime.

## Introducing JAWN (Just A Weather Notifier)

As a sneak peak, here is a recent notification from an alpha version of the app showing the weather condition as an emoji followed by the temperature per hour. A summary of the different weather emojis are in the title.

I have the app setup like an alarm clock. You tell the app your location and what times you would like to get a "weather notification alert". For me that would be at 6am in the morning, 12pm for lunch, and then 5pm before I leave from work. 

![JAWN](http://i.imgur.com/X217DNu.png "introducing JAWN")

As you can see the weather forecast at 12pm starts off with rain but should clear up by 10pm.

## Providing Weather Data

To have better control over who uses the app (because some people just want to see the world burn) I built a small REST API that merely does two things. Check the user's Google Sign-in token and then forward the weather request to a publicly available weather api. Below is my hourly forecast resource handler implemented with Jersey through DropWizard. You can see the rest at https://github.com/miryor/jawn-rest

``` java
    @POST
    @Timed(name="showAll-timed")
    @Metered(name="showAll-metered")
    @ExceptionMetered(name="showAll-exceptionmetered")
    public List hourlyForecast(
        @FormParam("token") @NotEmpty String token,
        @FormParam("location") @NotEmpty String location,
        @FormParam("version") @NotEmpty String version,
        @Context HttpServletRequest request
        ) {
       
        datastore.save(new TokenForecastRequest(token, location, request.getRemoteAddr(), new Date()) );
       
        CloseableHttpResponse response = null;
        InputStream in = null;
        List list = null;
        try {
            com.google.api.client.json.JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(googleClientId))
                .build();
           
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken != null) {
              Payload payload = idToken.getPayload();

              // Print user identifier
              String userId = payload.getSubject();
              String email = payload.getEmail();
              logger.debug("User ID: " + userId);
             
              datastore.save(new UserForecastRequest(email, location, request.getRemoteAddr(), new Date()) );
             
             
            } else {
                throw new WebApplicationException( "Could not authenticate your token", 403 );
            }
           
            list = hourlyForecastCache.getIfPresent(location);
           
            if ( list != null ) logger.debug( "Forecast for " + location + " found in cache" );

            if ( list == null ) {
                HttpGet httpGet = new HttpGet( String.format(wundergroundHourlyForecastResource, wundergroundApiKey, location) );
                response = httpClient.execute(httpGet);
                if ( response.getStatusLine().getStatusCode() == HttpStatus.OK_200 ) {
                    HttpEntity entity = response.getEntity();
                    in = entity.getContent();
                    WeatherJsonParser parser = new WundergroundWeatherJsonParser(location, in);
                    list = parser.parseHourlyForecast();
                    in.close();
                    in = null;
                    hourlyForecastCache.put(location,list);
                 }
                else {
                    if ( logger.isErrorEnabled() ) logger.error( "Error getting hourly forecast, got status " + response.getStatusLine().getStatusCode() );
                    throw new WebApplicationException( "Error getting hourly forecast", response.getStatusLine().getStatusCode() );
                }
            }
        }
        catch ( GeneralSecurityException e ) {
            if ( logger.isErrorEnabled() ) logger.error( "Error getting hourly forecast", e );
            throw new WebApplicationException( "Error getting hourly forecast", 500 );
        }
        catch ( IOException e ) {
            if ( logger.isErrorEnabled() ) logger.error( "Error getting hourly forecast", e );
            throw new WebApplicationException( "Error getting hourly forecast", 500 );
        }
        finally {
            if ( in != null ) try { in.close(); } catch ( Exception e ) {}
            if ( response != null ) try { response.close(); } catch ( Exception e ) {}
        }
        return list;
    }
```

## Getting Weather Data

On the app side its a bit more complicated as I am using the AlarmManager to schedule my notifications. Since the device might be sleeping I need the proper permission to wake the device up. Also if the device restarts I need the proper permission to run some code to re-add all my notifications. I also need to declare the receivers for both my notifications and when the device restarts and declare a service that will keep the device awake until the notification process has completed. Read more about AlarmManager here:
https://developer.android.com/reference/android/app/AlarmManager.html

Below is my complete AndroidManifest.xml. You can see all my requested permissions at the top and that I declare two receivers "NotificationPublisher" and "OnBootReceiver". The "OnBootReceiver" has the additional "intent-filter" of "BOOT_COMPLETED" that tells the Android OS to run "OnBootReceiver" when the device restarts. At the bottom you can see my declaration of a service "WeatherNotificationIntentService" which actually does all the hard work of downloading and parsing weather information:

``` xml
<manifest package="com.miryor.jawn" xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />

    <application android:allowbackup="true" android:debuggable="true" android:icon="@mipmap/ic_launcher" android:label="@string/app_name" android:supportsrtl="true" android:theme="@style/AppTheme">
        <!-- TODO: REMEMBER TO REMOVE android:debuggable -->
        <activity android:name=".MainActivity">
        </activity>
        <activity android:label="@string/name_sign_in_activity" android:name=".SignInActivity" android:theme="@style/ThemeOverlay.MyNoTitleActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN">

                <category android:name="android.intent.category.LAUNCHER">
            </category></action></intent-filter>
        </activity>
        <activity android:name=".AddNotifierActivity" />
        <activity android:label="@string/title_activity_view_hourly_forecast" android:name=".ViewHourlyForecastActivity" android:theme="@style/AppTheme.NoActionBar"></activity>

        <receiver android:name=".NotificationPublisher" />
        <receiver android:enabled="true" android:name=".OnBootReceiver">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED"></action>
            </intent-filter>
        </receiver>

        <service android:name=".WeatherNotificationIntentService" />

    </application>
</manifest>
```

This code schedules my notification to be triggered at a set time through the AlarmManager.

``` java
public static void setNotificationAlarm(Context context, Notifier notifier) {
    Intent notificationIntent = new Intent(context, NotificationPublisher.class);
    Bundle hackBundle = new Bundle();
    hackBundle.putParcelable(Notifier.EXTRA_NAME, notifier);
    notificationIntent.putExtra("hackBundle", hackBundle);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)notifier.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    Calendar calendar = Calendar.getInstance();
    long current = calendar.getTimeInMillis();
    calendar.set(Calendar.HOUR_OF_DAY, notifier.getHour());
    calendar.set(Calendar.MINUTE, notifier.getMinute());
    long alarmTime = calendar.getTimeInMillis();
    if ( alarmTime < current ) {
        calendar.add( Calendar.DAY_OF_YEAR, 1 );
        alarmTime = calendar.getTimeInMillis();
    }
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY, pendingIntent);

    Log.d( "JAWN", "Added alarm for " + notifier.getId() + "/" + notifier.getPostalCode() + " at " + calendar.getTime() );
}
```

You can see that I create an intent to be handled specifically by my "NotificationPublisher". The intent is then wrapped in a "broadcast" PendingIntent that gets stored in the AlarmManager. This is scheduled to be triggered at a certain time of day on a daily (INTERVAL_DAY) basis. I also specify that the device should wake up with "RTC_WAKEUP".

When the alarm is triggered the intent gets broadcasted looking for any receivers that are meant to handle it. In this case, I specified "NotificationPublisher". Below is the "NotificationPublisher" class which is a [WakefulBroadcastReceiver](https://developer.android.com/reference/android/support/v4/content/WakefulBroadcastReceiver.html), which is a special kind of broadcast receiver meant to handle a device wakeup event and lets you pass the work off to a service while keeping the device awake. This is the code that runs when the alarm is triggered. It in turn starts the "WeatherNotificationIntentService" through the "startWakefulService" method which will keep the device from going back to sleep while getting our weather data.

``` java
public class NotificationPublisher extends WakefulBroadcastReceiver {

    @Override    
    public void onReceive(Context context, Intent intent) {
        Bundle hackBundle = intent.getBundleExtra("hackBundle");
        Notifier notifier = hackBundle.getParcelable(Notifier.EXTRA_NAME);
        //Notifier notifier = intent.getParcelableExtra( Notifier.EXTRA_NAME );        Log.d( "JAWN", "Received alarm for " + notifier.getPostalCode() );
        Intent service = new Intent(context, WeatherNotificationIntentService.class);
        service.putExtra( Notifier.EXTRA_NAME, notifier );
        startWakefulService(context, service);
    }

    private void notifyError(Context context, String error) {
        Utils.sendNotification(context, Utils.RESULT_ERROR, error);
    }
}
```

And finally the WeatherNotificationIntentService that gets the Google Sign-in token of the user and then communicates with the REST API to get the weather forecast.

``` java
public class WeatherNotificationIntentService extends IntentService {

    private static final int MAX_ATTEMPTS = 5;

    public WeatherNotificationIntentService() {
        super( "WeatherNoticationIntentService" );
    }

    // TODO: Need to figure out DST
    @Override    
    protected void onHandleIntent(Intent intent) {

        Log.d( "JAWN", "onHandleIntent " + intent.getClass().getName() );

        // [START configure_signin]        // Configure sign-in to request the user's ID, email address, and basic        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();
        // [END configure_signin]
        // [START build_client]        // Build a GoogleApiClient with access to the Google Sign-In API and the        // options specified by gso.        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]
        try {
            Log.d("JAWN", "Connecting to Google API");
            ConnectionResult result = mGoogleApiClient.blockingConnect();
            if (result.isSuccess()) {
                GoogleSignInResult googleSignInResult =
                        Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient).await();
                handleSignInResult(googleSignInResult, intent);
            }
            else {
                Log.e("JAWN", "Could not connect to Google API " + result.getErrorCode() + ": " + result.getErrorMessage());
            }
        } finally {
            mGoogleApiClient.disconnect();
        }

        /*OptionalPendingResult opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);        if (!opr.isDone()) {            opr.setResultCallback(new ResultCallback() {                                      @Override                                      public void onResult(@NonNull GoogleSignInResult googleSignInResult) {                                          handleSignInResult(googleSignInResult, intent);                                      }                                  }            );        }        else {            handleSignInResult(opr.get(), intent);        }*/
    }

    private void handleSignInResult(GoogleSignInResult singInResult, Intent intent) {
        if (!singInResult.isSuccess()) {
            Utils.sendNotification(this, Utils.RESULT_ERROR, "You are not signed in.");
        }
        else {
            GoogleSignInAccount acct = singInResult.getSignInAccount();
            String token = acct.getIdToken();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notifier notifier = (Notifier) intent.getParcelableExtra(Notifier.EXTRA_NAME);

            Log.d("JAWN", "WeatherNotificationIntentService to get weather for " + notifier.getPostalCode());

            String provider = notifier.getProvider();
            String zipCode = notifier.getPostalCode();
            int dow = notifier.getDaysOfWeek();
            Calendar cal = Calendar.getInstance();
            int calDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (
                    (calDayOfWeek == Calendar.SUNDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_SUNDAY)) ||
                            (calDayOfWeek == Calendar.MONDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_MONDAY)) ||
                            (calDayOfWeek == Calendar.TUESDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_TUESDAY)) ||
                            (calDayOfWeek == Calendar.WEDNESDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_WEDNESDAY)) ||
                            (calDayOfWeek == Calendar.THURSDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_THURSDAY)) ||
                            (calDayOfWeek == Calendar.FRIDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_FRIDAY)) ||
                            (calDayOfWeek == Calendar.SATURDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_SATURDAY))
                    ) {
                if (
                        provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ||
                                provider.equals(JawnContract.WEATHER_API_PROVIDER_WUNDERGROUND)
                        ) {
                    int attempts = 0;
                    while ( attempts < MAX_ATTEMPTS ) {
                        try {
                            HourlyForecastFormatted result = loadJsonFromNetwork(this, notifier, provider, token, zipCode);
                            Utils.sendNotification(this, notifier.getId(), result);
                            // we got the weather, no further attempts needed                            break;
                        }
                        catch (InvalidTokenException e) {
                            Log.e("JAWN", getResources().getString(R.string.connection_error), e);
                            Utils.sendNotification(this, Utils.RESULT_ERROR, getResources().getString(R.string.connection_error) + ", invalid token");
                            // token is invalid, no further attempts needed                            break;
                        }
                        catch (IOException e) {
                            Log.e("JAWN", getResources().getString(R.string.connection_error), e);
                            // we got some weird exception, sleep for a minute*attempts                            try { Thread.sleep( (60000 * attempts) ); } catch ( InterruptedException e2 ) { break; }
                        }
                        attempts++;
                        if ( attempts >= MAX_ATTEMPTS ) Utils.sendNotification(this, Utils.RESULT_ERROR, getResources().getString(R.string.connection_error));
                    }
                } else {
                    Utils.sendNotification(this, Utils.RESULT_ERROR, "Wrong provider set: " + provider + ", could not download weather");
                }
            } else {
                Log.d("JAWN", "Notification not set for " + calDayOfWeek);
            }
        }

        NotificationPublisher.completeWakefulIntent(intent);
    }

    private HourlyForecastFormatted loadJsonFromNetwork(Context context, Notifier notifier, String provider, String token, String location) throws IOException {
        WeatherJsonGrabber g = null;
        if ( provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ) g = new JawnRestWeatherJsonGrabber(context, token, location);
        else g = new WundergroundWeatherJsonGrabber(location);

        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(g.getWeatherJsonInputStream(), "UTF-8"));
            char[] chars = new char[1024];
            int len;
            while ((len = reader.read(chars)) != -1) {
                builder.append(chars, 0, len);
            }
        }
        finally {
            if ( reader != null ) try { reader.close(); } catch ( IOException e ) {}
        }
        String forecast = builder.toString();
        notifier.setForecast( forecast );
        JawnContract.updateNotifierForecast(context, notifier);

        WeatherJsonParser p = null;
        if ( provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ) p = new JawnRestWeatherJsonParser( forecast );
        else p = new WundergroundWeatherJsonParser( forecast );

        List list = p.parseHourlyForecast();
        return Utils.formatHourlyForecastForNotification(list);
    }

}
```

The below is a rather straight forward attempt to match the correct emoji to different weather conditions. This is called for each hour's weather condition to get the emoji we want to display:

``` java
    public static String EMOJI_SUN = "\u2600";
    public static String EMOJI_CRESCENTMOON = "\uD83C\uDF19";
    public static String EMOJI_NIGHTSKY = "\uD83C\uDF03";
    public static String EMOJI_CITYDUSK = "\uD83C\uDF06";
    public static String EMOJI_CLOUD = "\u2601";
    public static String EMOJI_SUN_BEHIND_CLOUD = "\u26C5";
    public static String EMOJI_CLOUD_LIGHTNING_RAIN = "\u26C8";
    public static String EMOJI_SNOWMAN = "\u26C4";
    public static String EMOJI_UMBRELLA = "\u2614";
    public static String EMOJI_QUESTION = "\u2753";

    public static String[] SNOW_WORDS = {
            "hail", "flurries", "freezing", "ice", "sleet", "snow"
    };

    public static String[] RAIN_WORDS = {
            "drizzle", "rain"
    };

    public static String[] CLOUDY_WORDS = {
            "cloudy", "fog", "haze", "hazy", "mist", "overcast", "partly sunny"
    };

    public static String[] STORM_WORDS = {
            "thunderstorm"
    };

    public static String[] SUNNY_WORDS = {
            "sunny"
    };

    public static String[] CLEAR_WORDS = {
            "clear"
    };

    public static String getEmoji(String condition, int hour) {
        String emoji = EMOJI_QUESTION;
        for (int x = 0; x < STORM_WORDS.length; x++) {
            if (condition.indexOf(STORM_WORDS[x]) >= 0) {
                emoji = EMOJI_CLOUD_LIGHTNING_RAIN;
                return emoji;
            }
        }
        for (int x = 0; x < CLOUDY_WORDS.length; x++) {
            if (condition.indexOf(CLOUDY_WORDS[x]) >= 0) {
                emoji = EMOJI_CLOUD;
                return emoji;
            }
        }
        for (int x = 0; x < SNOW_WORDS.length; x++) {
            if (condition.indexOf(SNOW_WORDS[x]) >= 0) {
                emoji = EMOJI_SNOWMAN;
                return emoji;
            }
        }
        for (int x = 0; x < RAIN_WORDS.length; x++) {
            if (condition.indexOf(RAIN_WORDS[x]) >= 0) {
                emoji = EMOJI_UMBRELLA;
                return emoji;
            }
        }
        for (int x = 0; x < SUNNY_WORDS.length; x++) {
            if (condition.indexOf(SUNNY_WORDS[x]) >= 0) {
                emoji = EMOJI_SUN;
                return emoji;
            }
        }
        for (int x = 0; x < CLEAR_WORDS.length; x++) {
            if (condition.indexOf(CLEAR_WORDS[x]) >= 0) {
                if (hour >= 6 && hour <= 18) emoji = EMOJI_SUN;
                else emoji = EMOJI_NIGHTSKY;
                return emoji;
            }
        }
        return emoji;
    }
```

You can see the rest of the code on my github repositories:

https://github.com/miryor/JAWN

https://github.com/miryor/jawn-rest
