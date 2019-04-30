endpoint.sendSms = function(to, from, body) {
    var res = endpoint._sendSms({to: to, from: from, body: body});
    return res.sid;
};