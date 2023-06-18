import {isExist} from '@_utils/validation';
import {Horizon} from '@components/templates/defaultComps';
import {getW} from '@constants/appUnits';
import {BET_ENG_KOR, DEST_ENG_KOR} from '@constants/dataConfig';
import {COMMON_IC} from '@constants/imageMap';
import {Image_local} from '@platformPackage/Image';
import COLORS from '@styles/colors';
import font from '@styles/textStyle';
import {PressGoBack} from '@userInteraction/pressAction';
import React from 'react';
import {Text, View} from 'react-native';

const DestTag = ({name, style}) => (
  <View
    style={{
      backgroundColor: 'rgba(134, 134, 134, 0.1)',
      paddingVertical: getW(4),
      paddingHorizontal: getW(8),
      borderRadius: getW(4),
      ...style,
    }}>
    <Text style={[font.eb12, {color: COLORS.gray32}]}>
      {DEST_ENG_KOR[name]}
    </Text>
  </View>
);

export const DetailComp = {
  Header: ({routeNumber, routeDirection, style}) => (
    <Horizon
      style={{
        height: getW(65),
        alignItems: 'center',
        backgroundColor: 'white',

        ...style,
      }}>
      <View
        horizon
        style={{paddingLeft: getW(18), flex: 1, alignItems: 'flex-start'}}>
        <PressGoBack>
          <Image_local
            style={{width: getW(24), height: getW(24)}}
            source={COMMON_IC.arrow_back}
          />
        </PressGoBack>
      </View>
      <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
        <Text style={[font.b20, {color: COLORS.gray48}]}>
          {routeNumber + ' 시간표'}
        </Text>
        <Text style={[font.b12, {color: COLORS.gray48, marginTop: getW(6)}]}>
          {BET_ENG_KOR[routeDirection] + ' 방면'}
        </Text>
      </View>
      <View style={{flex: 1}} />
    </Horizon>
  ),
  DestInfo: ({destinations, style}) =>
    isExist(destinations) ? (
      <View
        style={{
          paddingVertical: getW(14),
          paddingHorizontal: getW(18),
          backgroundColor: 'white',
          ...style,
        }}>
        <Text style={[font.b10, {color: COLORS.gray48, marginTop: getW(6)}]}>
          경유 지역
        </Text>
        <Horizon style={{flexWrap: 'wrap'}}>
          {destinations.map(destName => (
            <DestTag
              key={destName}
              name={destName}
              style={{marginTop: getW(6), marginRight: getW(12)}}
            />
          ))}
        </Horizon>
      </View>
    ) : null,
  FirstEndInfo: ({firstTime, endTime, style}) =>
    isExist(firstTime) ? (
      <Horizon style={{height: getW(68), paddingVertical: getW(10), ...style}}>
        <View
          style={{
            height: getW(48),
            justifyContent: 'space-between',
            alignItems: 'center',
            flex: 1,
          }}>
          <Text style={[font.eb12, {color: COLORS.gray48}]}>첫차</Text>
          <Text style={[font.b20, {color: COLORS.main29}]}>{firstTime}</Text>
        </View>
        <View
          style={{
            height: getW(48),
            justifyContent: 'space-between',
            alignItems: 'center',
            flex: 1,
          }}>
          <Text style={[font.eb12, {color: COLORS.gray48}]}>막차</Text>
          <Text style={[font.b20, {color: COLORS.main29}]}>{endTime}</Text>
        </View>
      </Horizon>
    ) : null,
  TimeElem: ({timeInfo, isNearest, isLast, style}) => {
    const {type, time, timeLeft} = timeInfo;

    return (
      <Horizon>
        <View style={{flex: 1}} />
        <View style={{...style, alignItems: 'center'}}>
          <Text
            style={[
              font.b16,
              {
                color:
                  type === 'PAST'
                    ? 'rgba(0, 41, 90, 0.5)'
                    : type === 'LEFT'
                    ? COLORS.main29
                    : COLORS.main,
                textDecorationLine: type === 'PAST' ? 'line-through' : 'none',
              },
            ]}>
            {time}
          </Text>
          {isLast ? null : (
            <View
              style={{
                height: getW(10),
                width: 1,
                backgroundColor: COLORS.main20P,
                marginVertical: getW(12),
              }}
            />
          )}
        </View>
        <View style={{flex: 1}}>
          {type === 'NEAR' ? (
            <Horizon style={{alignItems: 'center'}}>
              <View
                style={{
                  width: getW(10),
                  height: 2,
                  marginHorizontal: getW(12),
                  backgroundColor: COLORS.main,
                }}
              />
              <Text style={[font.b12, {color: COLORS.main}]}>
                {timeLeft + '분 후 출발'}
              </Text>
            </Horizon>
          ) : null}
        </View>
      </Horizon>
    );
  },
};
