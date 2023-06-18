import {Dimensions} from 'react-native';

export const browserAgent = navigator.userAgent;

function getOS() {
  var userAgent = window.navigator.userAgent,
    platform =
      window.navigator?.userAgentData?.platform || window.navigator.platform,
    macosPlatforms = ['Macintosh', 'MacIntel', 'MacPPC', 'Mac68K'],
    windowsPlatforms = ['Win32', 'Win64', 'Windows', 'WinCE'],
    iosPlatforms = ['iPhone', 'iPad', 'iPod'],
    os = null;

  if (macosPlatforms.indexOf(platform) !== -1) {
    os = 'mac';
  } else if (iosPlatforms.indexOf(platform) !== -1) {
    os = 'ios';
  } else if (windowsPlatforms.indexOf(platform) !== -1) {
    os = 'windows';
  } else if (/Android/.test(userAgent)) {
    os = 'android';
  } else if (/Linux/.test(platform)) {
    os = 'linux';
  }

  return os;
}

export const DESIGN_PIXEL_WIDTH = 360;
export const DESING_PIXEL_HEIGHT = 800;
export const MINIMUM_RATIO = 1.65;
export const REAL_WIDTH = Dimensions.get('window').width;
export const REAL_HEIGHT = Dimensions.get('window').height;
export const REAL_RATIO = REAL_HEIGHT / REAL_WIDTH;
export const IS_THICK = REAL_RATIO < MINIMUM_RATIO;
export const WINDOW_WIDTH = IS_THICK
  ? (REAL_WIDTH * REAL_RATIO) / MINIMUM_RATIO
  : REAL_WIDTH;
export const WINDOW_HEIGHT = Dimensions.get('window').height;

export const getW = designPixel => {
  return Math.round((designPixel * WINDOW_WIDTH) / DESIGN_PIXEL_WIDTH);
};
export const getWFloat = designPixel => {
  return (
    Math.round((designPixel * WINDOW_WIDTH * 10) / DESIGN_PIXEL_WIDTH) / 10
  );
};
export const getH = designPixel => {
  return Math.round((designPixel * WINDOW_HEIGHT) / DESING_PIXEL_HEIGHT);
};
export const getHFloat = designPixel => {
  return (
    Math.round((designPixel * WINDOW_HEIGHT * 10) / DESING_PIXEL_HEIGHT) / 10
  );
};
export const TABBAR_HEIGHT_ROUGH = getH(130);

export const STACK_HEADER_HEIGHT = getW(100);

export const THICK_PADDING = IS_THICK ? (REAL_WIDTH - WINDOW_WIDTH) / 2 : 0;
