'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var LocationSchema = new Schema({
  userid: String,
  geo: {
    type: [Number],
    index: '2d'
  },
  time : { type : Date, default: Date.now }
});


module.exports = mongoose.model('Location', LocationSchema);
