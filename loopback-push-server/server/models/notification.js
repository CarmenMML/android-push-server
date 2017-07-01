 module.exports = function(Notification) {

   var badge = 1;

    //DEFINING A PROPERTY IN NOTIFICATION FOR SENDING  PUSH MESSAGE
    Notification.sendMessage = function(message, registrationId, from, callback) {
          var app = this.app;
        from = from? from:'COMPANY_NAME';
        sendMessage(app, message, registrationId, from, callback);
    }//sendMessage Notification method..



    //FUNCTION FOR SENDING PUSH MESSAGE..
    var sendMessage = function(app, message, registrationId, from, callback){
      var Application = app.models.application;
      var PushModel = app.models.push;

      var note = new Notification({
        expirationInterval: 3600, // Expires 1 hour from now.
        badge: badge++,
        //  sound: 'ping.aiff',
        message: message,
        messageFrom: from
      });

      PushModel.notifyById(registrationId, note, function (err) {
        if (err) {
          console.error('Cannot notify %j: %s', registrationId, err.stack);
          return callback(err);
        }
        console.log('Pushing notification to %j', registrationId);
        callback(null, []);
      });
    }//sendMessage function




  //Now registering the method
    Notification.remoteMethod(
        'sendMessage',
        {
          accepts: [{arg: 'message', type: 'string', required:true},
          {arg: 'registrationId', type: 'string', required:true},
          {arg: 'from', type: 'string', required:true}],
          description: "Sending message from notification.."
        }
    )

}//module.exports
