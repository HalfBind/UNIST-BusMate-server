import {isEmpty} from './validation';

export function getPairList(origin) {
  if (isEmpty(origin)) {
    return [];
  }
  const pairList = [...Array(Math.ceil(origin.length / 2))].map((no, index) => {
    const pair = [origin[index * 2]];
    if (index * 2 + 1 < origin.length) {
      pair.push(origin[index * 2 + 1]);
    }
    return pair;
  });
  return pairList;
}
export function pairsToFlat(pairList) {
  if (isEmpty(pairList)) {
    return [];
  }
  return pairList.reduce(
    (out, curPair) => {
      const {flatlist: lastList} = out;
      return {flatlist: [...lastList, ...curPair]};
    },
    {flatlist: []},
  ).flatlist;
}

export function getDateString(dateObj, option = 'default') {
  if (isEmpty(dateObj.getFullYear())) {
    console.log('date obj empty: ', dateObj);
    return '';
  }
  const year = String(dateObj.getFullYear());
  let month = String(dateObj.getMonth() + 1);
  if (month.length === 1) {
    month = '0' + month;
  }
  let date = String(dateObj.getDate());
  if (date.length === 1) {
    date = '0' + date;
  }

  if (option === 'default') {
    return year + '.' + month + '.' + date;
  } else {
    return year + '년 ' + month + '월 ' + date + '일';
  }
}

export function getTimeString(dateObj) {
  let hour = String(dateObj.getHours());
  if (hour.length === 1) {
    hour = '0' + hour;
  }
  let minutes = String(dateObj.getMinutes());
  if (minutes.length === 1) {
    minutes = '0' + minutes;
  }
  return `${hour}:${minutes}`;
}

export function timeToNum(timeString) {
  let [hour, minute] = timeString.split(':');
  return Number(hour + minute);
}

export function getAPMTime(timeString) {
  let [hour, minute] = timeString.split(':');
  let pref = '오전';
  if (Number(hour) > 12) {
    hour = String(Number(hour) - 12);
    pref = '오후';
  }
  if (hour.length === 1) {
    hour = '0' + hour;
  }
  if (minute.length === 1) {
    minute = '0' + minute;
  }

  return pref + ' ' + hour + ':' + minute;
}

export function getMinuteLeft(targetMinute) {
  const diffDateTime = new Date(`0000/01/01 ${targetMinute}:00`).getTime();
  const nowDateTime = new Date(
    `0000/01/01 ${getTimeString(new Date())}:00`,
  ).getTime();
  const diffMin = (diffDateTime - nowDateTime) / (60 * 1000);
  return Math.floor(diffMin);
}
