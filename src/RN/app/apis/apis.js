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
      console.log('error from getBusList', error);
    }
  },
};

export default API;
