// Copyright IBM Corp. 2013,2015. All Rights Reserved.
// Node module: loopback-component-push
// This file is licensed under the Artistic License 2.0.
// License text available at https://opensource.org/licenses/Artistic-2.0

'use strict';

describe('Installation', function() {
  var registration = null;

  it('registers a new installation', function(done) {
    var token = 'eFuIdT7GD9Y:APA91bG2GHGiszkCqLVf_HO_jRKds3pQvSLSAz9pzGeAXYZpj88RqKZkHyK07BZlOTYoztJ4m6e9NUQ8xe1bU3xEHsaU8Di8DBytco8K4YL57aNV6d5rqTWa36ZpnTwO_GSFbHpH3qqv' +
      'f2a19241 e8f33305';
    Installation.create({
      appId: 'ASVproject',
      appVersion: '1',
      userId: 'raymond',
      deviceToken: token,
      deviceType: 'android',
      created: new Date(),
      modified: new Date(),
      status: 'Active',
    }, function(err, result) {
      if (err) {
        console.error(err);
        done(err, result);
        return;
      } else {
        var reg = result;
        assert.equal(reg.appId, 'ASVproject');
        assert.equal(reg.userId, 'raymond');
        assert.equal(reg.deviceType, 'android');
        assert.equal(reg.deviceToken, token);

        assert(reg.created);
        assert(reg.modified);

        registration = reg;

        Installation.findByApp('android', 'ASVproject', function(err, results) {
          assert(!err);
          assert.equal(results.length, 1);
          var reg = results[0];
          assert.equal(reg.appId, 'ASVproject');
          assert.equal(reg.userId, 'raymond');
          assert.equal(reg.deviceType, 'android');
          assert.equal(reg.deviceToken, token);
          done(err, results);
        });
      }
    });
  });
});
