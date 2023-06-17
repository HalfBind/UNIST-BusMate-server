import {getW} from '@constants/appUnits';
const light = {fontFamily: 'NanumSquare', fontWeight: 300};
const regular = {fontFamily: 'NanumSquare', fontWeight: 400};
const bold = {fontFamily: 'NanumSquare', fontWeight: 700};
const extraBold = {fontFamily: 'NanumSquare', fontWeight: 800};
// const generateTextComponent = ()
const getLH = (fontsize, ratio) => {
  const height = Math.round(getW(fontsize) * ratio);
  return height;
};

const mf = (fontConf, size) => ({...fontConf, fontSize: getW(size)});

const font = {
  //title, cartitem title
  light,
  regular,

  bold,
  extraBold,
  eb8: mf(extraBold, 8),
  b8: mf(bold, 8),

  bold16: {
    ...bold,
    fontSize: getW(16),
    color: 'black',
  },
};
export default font;
