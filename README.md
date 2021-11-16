---
title: Twilio endpoint
keywords: 
last_updated: August 8, 2018
tags: []
summary: "Detailed description of the API of the Twilio endpoint."
---

## Overview

The Twilio endpoint has the following features:
 
- Authentication
- Sending of SMS

## Quick start

Once you have configured the endpoint, you can send a SMS like this:

```js
var sid = app.endpoints.twilio.sendSms('+19862148795', '+18032158946', 'test message!');
log('sid: ' + sid);
```

## Configuration

You will need an account in Twilio and have the API credentials.

### Account SID

This is the ID of your Twilio account.

### Auth Token

This is the auth token for your Twilio account.

## Javascript API

### Send SMS

```js
var sid = app.endpoints.twilio.sendSms(to, from, body);
```

Allows to send a SMS to a phone number. The `from` number is a phone number in your Twilio account.

Sample:

```js
var sid = app.endpoints.twilio.sendSms('+19862148795', '+18032158946', 'test message!');
log('sid: ' + sid);
```

## Events

This endpoint does not have events.

## About SLINGR

SLINGR is a low-code rapid application development platform that accelerates development, with robust architecture for integrations and executing custom workflows and automation.

[More info about SLINGR](https://slingr.io)

## License

This endpoint is licensed under the Apache License 2.0. See the `LICENSE` file for more details.
