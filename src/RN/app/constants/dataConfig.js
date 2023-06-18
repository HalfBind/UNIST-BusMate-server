import {getTimeString} from '@_utils/converters';

export const initialContext = {
  inited: false,
  isSigned: false,
  userName: null,
  dest: 'GUYEONG_RI',
  time: getTimeString(new Date()),
  mode: 'leave',
};

export const initialBusDetail = {
  loadState: 'loading',
  timeList: [],
  startTime: null,
  endTime: null,
  destinations: [],
};

export const DAYS = {
  MON: '월요일',
  TUE: '화요일',
  WED: '수요일',
  THU: '목요일',
  FRI: '금요일',
  SAT: '토요일',
  SUN: '일요일',
};

export const DEST_ENG_KOR = {
  ULSAN_UNIVERSITY: '울산대학교',
  SINBOK_ROTARY: '신복로터리',
  EONYANG_TERMINAL: '언양터미널',
  KTX_ULSAN_STATION: 'KTX 울산역',
  ULSAN_TERMINAL: '울산터미널',
  SEONGNAM: '성남동',
  SAMSAN: '삼산동',
  INDUSLTRIAL_COMPLEX_CAMPUS: 'UNIST 산학융합캠퍼스',
  GUYEONG_RI: '구영리',
};
export const DEST_KOR_ENG = {
  울산대학교: 'ULSAN_UNIVERSITY',
  신복로터리: 'SINBOK_ROTARY',
  언양터미널: 'EONYANG_TERMINAL',
  'KTX 울산역': 'KTX_ULSAN_STATION',
  울산터미널: 'ULSAN_TERMINAL',
  성남동: 'SEONGNAM',
  삼산동: 'SAMSAN',
  'UNIST 산학융합캠퍼스': 'INDUSLTRIAL_COMPLEX_CAMPUS',
  구영리: 'GUYEONG_RI',
};

export const BET_KOR_ENG = {
  율리차고지: 'YULI_GARAGE',
  웰컴센터: 'WELCOME_CENTER',
  태화강역: 'TAEWHA_RIVER_STATION',
  꽃바위: 'KKOTBAWI',
  덕하차고지: 'DEOKHA_GARAGE',
  농소차고지: 'NONGSO_GARAGE',
  유니스트: 'UNIST',
  태화강: 'TAEWHA_RIVER',
  삼남신화: 'SAMNAM_SINWHA',
};

export const BET_ENG_KOR = {
  YULI_GARAGE: '율리차고지',
  WELCOME_CENTER: '웰컴센터',
  TAEWHA_RIVER_STATION: '태화강역',
  KKOTBAWI: '꽃바위',
  DEOKHA_GARAGE: '덕하차고지',
  NONGSO_GARAGE: '농소차고지',
  UNIST: '유니스트',
  TAEWHA_RIVER: '태화강',
  SAMNAM_SINWHA: '삼남신화',
};
