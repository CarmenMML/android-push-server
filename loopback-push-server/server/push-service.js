 module.exports = function(app) {
    var Notification = app.models.notification;
    var Application = app.models.application;
    var PushModel = app.models.push;
    function startPushServer() {

    PushModel.on('error', function(err) {
      console.error('Push Notification error: ', err.stack);
    });

    // Pre-register an application that is ready to be used for testing.
    // You should tweak config options in ./config.js

    var config = require('./config');

    var loopbackApp = {
      id: 'loopback-push-server',
      userId: 'strongloop',
      name: config.appName,

      description: 'loopback Push Notification Service',
      pushSettings: {
        apns: {
          certData: "APPLE CERT. DATA",
          keyData: "APPLE KEY DATA",
          pushOptions: {
            // Extra options can go here for APN
          },
          feedbackOptions: {
            batchFeedback: true,
            interval: 300
          }
        },
        gcm: {
          serverApiKey: config.gcmServerApiKey
        }
      }
    };



    updateOrCreateApp(function(err, appModel) {
      if (err) {
        throw err;
      }
      console.log('Application id: %j', appModel.id);
    });


    function updateOrCreateApp(cb) {
      Application.findOne({
          where: {
            id: loopbackApp.id
          }
        },
        function(err, result) {
          if (err) cb(err);
          if (result) {
            delete loopbackApp.id;
            result.updateAttributes(loopbackApp, cb);
          } else {
            return register(cb);
          }
        });
    } //updateOrCreate function



    Application.beforeSave = function(next) {
      if (this.name === loopbackApp.name) {
        this.id = 'loopback-push-server';
      }
      next();
    };

    Application.register(
      loopbackApp.userId,
      loopbackApp.name, {
        description: loopbackApp.description,
        pushSettings: loopbackApp.pushSettings
      },
      function(err, app) {
        if (err) {
          return cb(err);
        }
        return cb(null, app);
      }
    );
    } //register App

    //startPushServer

    startPushServer()
    }
