module.exports = function (app) {
  var Notification = app.models.notification;
  var Application = app.models.application;
 var PushModel = app.models.push;

  function startPushServer() {
// Add our custom routes

    var badge = 1;
    app.post('/notify/:id', function (req, res, next) {
      var note = new Notification({
        expirationInterval: 3600, // Expires 1 hour from now.
        badge: badge++,
        sound: 'ping.aiff',
        alert: 'Hello',
        messageFrom: 'Ray'
      });

      PushModel.notifyById(req.params.id, note, function (err) {
        if (err) {
          // let the default error handling middleware report the error in an appropriate way
          console.error('Cannot notify %j: %s', req.params.id, err.stack);
          return next(err);
        }
        console.log('pushing notification to %j', req.params.id);
        res.send(200, 'OK');
      });
    });

    PushModel.on('error', function (err) {
      console.error('Push Notification error: ', err.stack);
    });

// Pre-register an application that is ready to be used for testing.
// You should tweak config options in ./config.js

    var config = require('./config');

    var demoApp = {
      id: 'loopback-push-server',
      userId: 'cmml555@gmail.com',
      name: config.appName,

      description: 'LoopBack Push Notification Demo Application',
      pushSettings: {
        apns: {
          certData: config.apnsCertData,
          keyData: config.apnsKeyData,
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


    updateOrCreateApp(function (err, appModel) {
      if (err) {
        throw err;
      }
      console.log('Application id: %j', appModel.id);
    });

//--- Helper functions ---
    function updateOrCreateApp(cb) {
      Application.findOne({
          where: { id: demoApp.id }
        },
        function (err, result) {
          if (err) cb(err);
          if (result) {
            console.log('Updating application: ' + result.id);
            delete demoApp.id;
            result.updateAttributes(demoApp, cb);
          } else {
            return registerApp(cb);
          }
        });
    }

    function registerApp(cb) {
      console.log('Registering a new Application...');
      // Hack to set the app id to a fixed value so that we don't have to change
      // the client settings
      Application.beforeSave = function (next) {
       // if (this.name === demoApp.name) {
          this.id = 'loopback-push-server';
        //}
        next();
      };
      Application.register(
        demoApp.userId,
        demoApp.name,
        {
          description: demoApp.description,
          pushSettings: demoApp.pushSettings
        },
        function (err, app) {
          if (err) {
            return cb(err);
          }
          return cb(null, app);
        }
      );
    }
  }

  startPushServer();
};



