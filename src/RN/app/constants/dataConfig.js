import {getTimeString} from '@_utils/converters';

export const initialContext = {
  inited: false,
  isSigned: false,
  userName: null,
  dest: 'GUYEONG_RI',
  time: getTimeString(new Date()),
  mode: 'leave',
};

export const TEST_DEST_LIST = [
  'SEONGNAM',
  'ULSAN_UNIVERSITY',
  'GUYEONG_RI',
  'DEOKHA_GARAGE',
  'NONGSO_GARAGE'
]