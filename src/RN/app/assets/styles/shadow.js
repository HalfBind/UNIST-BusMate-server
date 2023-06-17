import {getW} from '@constants/appUnits';
import COLORS from './colors';
import {StyleSheet} from 'react-native';

const SHADOW = StyleSheet.create({
  basic: {
    shadowColor: COLORS.black8p,
    shadowOffset: {
      width: getW(6),
      height: getW(12),
    },
    shadowRadius: getW(24),
  },
  homeCard: {
    shadowColor: COLORS.black8p,
    shadowOffset: {
      width: 0,
      height: 0,
    },
    shadowRadius: getW(26),
  },
  homeDestBtn: {
    shadowColor: COLORS.black25P,
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowRadius: getW(4),
  },
  modeSmall: {
    shadowColor: COLORS.black25P,
    shadowOffset: {
      width: 0,
      height: getW(2),
    },
    shadowRadius: getW(4),
  },
});

export default SHADOW;