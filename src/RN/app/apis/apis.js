import axios from 'axios';

const BASE = 'http://localhost:8080/UNIST-BusMate/';

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
  getBusWithRoute: API_ORIGIN.bus,
};

export default API;
