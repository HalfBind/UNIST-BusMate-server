import {getTimeString} from '@_utils/converters';
import {IS_SPRING} from 'app/appConfig';
import axios from 'axios';
const BASE = IS_SPRING
  ? 'http://localhost:8080/UNIST-BusMate/api/'
  : 'http://localhost:8080/api/';
const DEST = BASE + 'destinationInfos/';
const BUS = BASE + 'buses/';
const NOTI = BASE + 'bookmarks/';

const API_ORIGIN = {
  bus: async ({routeNumber}) => {
    try {
      const res = await axios({
        url: BASE + 'buses/' + routeNumber,
        method: 'get',
      });
      return res;
    } catch (error) {
      console.log('error from bus', error);
    }
  },
};

const API = {
  getBusList: async ({dest, mode, time}) => {
    console.log('time : ', time);
    const add =
      mode === 'leave'
        ? `${dest}?departureTime=${time}`
        : `${dest}/until/${time}?departureTime=${getTimeString(new Date())}`;
    try {
      const res = await axios({
        url: DEST + add,
        method: 'get',
      });
      return res;
    } catch (error) {
      console.log('error from getBusList', error.response.status, error);
      if (error.response.status === 404) {
        return {data: []};
      }
    }
  },
  getTimeTable: async ({routeNumber, routeDirection}) => {
    try {
      const res = await axios({
        url: BUS + routeNumber + '/' + routeDirection,
        method: 'get',
      });
      return res;
    } catch (error) {
      console.log('error from get timetable', error);
      if (error.response.status === 404) {
        return {data: []};
      }
    }
  },
  saveAlarm: async ({busId, userName, days, timeOffset}) => {
    try {
      const data = {busId, userName, days, timeOffset};
      console.log('save alarm body?', data);
      const res = await axios({
        url: NOTI,
        method: 'post',
        data,
      });
      return res;
    } catch (error) {
      console.log('error from save  alarm', error);
    }
  },
  getAlarmList: async ({userName}) => {
    try {
      const res = await axios({
        url: NOTI + userName,
        method: 'get',
      });
      return res;
    } catch (error) {
      if (error.response.status === 404) {
        return {data: []};
      }
      console.log('error from get alarmlist', error);
    }
  },
  deleteAlarm: async ({alarmId}) => {
    try {
      const res = await axios({
        url: NOTI + alarmId,
        method: 'delete',
      });
      return res;
    } catch (error) {
      console.log('error from delete alarm', error);
    }
  },
  // register : async({userName}) => {
  //   try {
  //     const res = await axios({
  //       url :
  //     })
  //   }
  // }
};

export default API;
