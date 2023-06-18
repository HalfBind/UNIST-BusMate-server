const prefix = '/UNIST-BusMate/';

export const navigationConfig = {
  RootStackNavigator: {
    child: [
      'Home',
      'AddAlarm',
      'AlarmList',
      'BusDetail',
      'Register',
      'HomeSetTime',
      'HomeSetDest',
    ],
    paths: {},
  },
  Home: {
    paths: {},
    linkConfig: {path: prefix},
  },
  AddAlarm: {
    paths: {},
    linkConfig: {
      path: prefix + 'add-alarm',
    },
  },
  AlarmList: {
    paths: {},
    linkConfig: {
      path: prefix + 'alarm-list',
    },
  },
  BusDetail: {
    paths: {},
    linkConfig: {
      path: prefix + 'bus',
    },
  },
  Register: {
    paths: {},
    linkConfig: {
      path: prefix + 'register',
    },
  },
  HomeSetTime: {
    paths: {},
    linkConfig: {
      path: prefix + 'set-time',
    },
  },
  HomeSetDest: {
    paths: {},
    linkConfig: {
      path: prefix + 'set-dest',
    },
  },
};
