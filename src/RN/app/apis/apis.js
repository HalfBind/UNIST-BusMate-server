import {getTimeString} from '@_utils/converters';
import axios from 'axios';

const BASE = 'http://localhost:8080/UNIST-BusMate/api/';
const DEST = BASE + 'destinationInfos/';
const BUS = BASE + 'buses/';

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
};

export default API;
