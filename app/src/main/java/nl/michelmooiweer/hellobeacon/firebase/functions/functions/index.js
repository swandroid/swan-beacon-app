const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();


exports.sendAlert = functions.https.onCall((data) => {
    
    const targetDevices = data.targetDevices;
    const alertMessage = data.alertMessage;
    
    const payload = {
          notification: {
            title: 'Alert!',
            body: alertMessage
          }
        };
    return admin.messaging().sendToDevice(targetDevices, payload)
});
    
