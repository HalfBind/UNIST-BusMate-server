import {getAPMTime, getMinuteLeft} from '@_utils/converters';
import {DoNothing} from '@_utils/handling';
import {Horizon} from '@components/templates/defaultComps';
import {getW} from '@constants/appUnits';
import {BET_ENG_KOR, DEST_ENG_KOR} from '@constants/dataConfig';
import {COMMON_IC} from '@constants/imageMap';
import {Image_local} from '@platformPackage/Image';
import COLORS from '@styles/colors';
import SHADOW from '@styles/shadow';
import font from '@styles/textStyle';
import {PressCallback, PressNavigate} from '@userInteraction/pressAction';
import React from 'react';
import {StyleSheet, Text, View} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';

const ModeBtn = ({curMode, onSelect}) => (
  <PressCallback
    style={ST.modeBtn}
    onPress={() => {
      if (curMode === 'leave') {
        onSelect('arrive');
      } else {
        onSelect('leave');
      }
    }}>
    <View style={curMode === 'arrive' ? ST.modeInOn : ST.modeInOff}>
      <Text style={curMode === 'arrive' ? ST.modeInTOn : ST.modeInTOff}>
        도착
      </Text>
    </View>
    <View style={curMode === 'leave' ? ST.modeInOn : ST.modeInOff}>
      <Text style={curMode === 'leave' ? ST.modeInTOn : ST.modeInTOff}>
        출발
      </Text>
    </View>
  </PressCallback>
);

export const HomeComp = {
  Header: ({homeState, onModeSelect = DoNothing, onRefresh = DoNothing}) => {
    const {dest, time, mode} = homeState;

    return (
      <View>
        <Horizon
          style={{
            paddingVertical: getW(12),
            paddingHorizontal: getW(16),
            backgroundColor: 'white',
          }}>
          <PressNavigate
            horizon
            style={{...SHADOW.homeDestBtn, borderRadius: getW(6)}}
            routeName={'HomeSetDest'}>
            <LinearGradient
              style={ST.destBtn}
              start={{x: 0, y: 0}}
              end={{x: 1, y: 1}}
              colors={['rgba(0, 116, 255, 1)', 'rgba(14, 139, 209, 1)']}>
              <View style={{justifyContent: 'space-between'}}>
                <Text style={[font.eb10, {color: 'white'}]}>목적지</Text>
                <Text
                  style={[font.eb16, {color: 'white', marginBottom: getW(12)}]}>
                  {DEST_ENG_KOR[dest]}
                </Text>
              </View>
              <Image_local
                source={COMMON_IC.arrow_down}
                style={{
                  width: getW(24),
                  height: getW(24),
                  alignSelf: 'flex-end',
                  marginBottom: getW(9),
                }}
              />
            </LinearGradient>
          </PressNavigate>
          <View style={{justifyContent: 'space-between', marginLeft: getW(22)}}>
            <PressNavigate
              horizon
              routeName={'HomeSetTime'}
              style={{
                alignItems: 'center',
                width: getW(84),
                justifyContent: 'space-between',
              }}>
              <Text style={[font.b12, {color: COLORS.main29}]}>
                {getAPMTime(time)}
              </Text>
              <Image_local
                source={COMMON_IC.drop_down}
                style={{width: getW(24), height: getW(24)}}
              />
            </PressNavigate>
            <Text style={[font.eb8, {alignSelf: 'center'}]}>
              {mode === 'leave' ? '이후' : '이전'}
            </Text>
            <ModeBtn curMode={mode} onSelect={onModeSelect} />
          </View>
          <View
            style={{
              height: getW(44),
              width: getW(2),
              marginHorizontal: getW(20),
              alignSelf: 'center',
              backgroundColor: COLORS.main10P,
            }}
          />
          <PressCallback style={{alignSelf: 'center'}} onPress={onRefresh}>
            <Image_local
              style={{width: getW(24), height: getW(24)}}
              source={COMMON_IC.refresh}
              tint={COLORS.main29}
            />
          </PressCallback>
        </Horizon>
        <LinearGradient
          start={{x: 0, y: 0}}
          end={{x: 1, y: 1}}
          colors={['rgba(0, 116, 255, 1)', 'rgba(0, 163, 255, 1)']}
          style={{
            width: '100%',
            height: getW(2),
            backgroundColor: COLORS.main20P,
            opacity: 0.2,
          }}
        />
      </View>
    );
  },
  BusInfo: ({busInfo, style}) => {
    const {routeNumber, routeDirection, departureTime, arrivalTime, busId} =
      busInfo;
    return (
      <View style={[ST.busCont, style]}>
        <Horizon style={{justifyContent: 'space-between'}}>
          <Horizon>
            <Text style={[font.b36, {color: COLORS.main}]}>{routeNumber}</Text>
            <Text
              style={[
                font.b12,
                {
                  color: COLORS.main29,
                  marginLeft: getW(15),
                  marginTop: getW(4),
                },
              ]}>
              {BET_ENG_KOR[routeDirection] + '\n방면'}
            </Text>
          </Horizon>
          <View style={{alignItems: 'flex-end'}}>
            <Text style={[font.b16, {color: COLORS.red}]}>
              {getMinuteLeft(departureTime) + '분 후 출발'}
            </Text>
            <Text style={[font.b16, {color: 'black'}]}>
              {arrivalTime + ' 도착'}
            </Text>
          </View>
        </Horizon>
        <Horizon style={{justifyContent: 'space-between'}}>
          <PressNavigate
            horizon
            style={{alignItems: 'center'}}
            routeName={'BusDetail'}
            extraParam={{routeNumber, routeDirection}}>
            <Image_local
              source={COMMON_IC.time_table}
              style={{
                width: getW(16),
                height: getW(16),
                marginRight: getW(4),
              }}
            />
            <Text style={[font.b12, {color: COLORS.main29}]}>시간표</Text>
          </PressNavigate>
          <PressNavigate
            horizon
            style={{alignItems: 'center'}}
            routeName={'AddAlarm'}
            extraParam={{routeNumber, busId, departureTime}}>
            <Image_local
              source={COMMON_IC.add_alarm}
              style={{
                width: getW(16),
                height: getW(16),
                marginRight: getW(4),
              }}
            />
            <Text style={[font.b12, {color: COLORS.main29}]}>알림 예약</Text>
          </PressNavigate>
        </Horizon>
      </View>
    );
  },
  AlarmListBtn: ({style}) => {
    return (
      <PressNavigate
        style={{
          borderRadius: getW(12),
          borderColor: 'rgba(0, 116, 255, 0.44)',
          borderWidth: 1,
          padding: getW(10),
          ...SHADOW.xy04p25r4,
          backgroundColor: 'white',
          ...style,
        }}
        routeName={'AlarmList'}>
        <Image_local
          source={COMMON_IC.access_alarm}
          style={{width: getW(24), height: getW(24)}}
        />
      </PressNavigate>
    );
  },
};

const ST = StyleSheet.create({
  destBtn: {
    width: getW(150),
    height: getW(60),
    paddingLeft: getW(12),
    paddingTop: getW(12),
    paddingRight: getW(6),
    flexDirection: 'row',
    // backgroundColor: COLORS.gradBlue,
    justifyContent: 'space-between',
    borderRadius: getW(6),
  },
  modeBtn: {
    width: getW(86),
    height: getW(22),

    flexDirection: 'row',
    borderRadius: getW(4),
    backgroundColor: COLORS.main20P,
    alignItems: 'center',
  },
  modeInOn: {
    flex: 1,
    height: getW(20),
    margin: getW(2),
    ...SHADOW.modeSmall,
    borderRadius: getW(4),
    backgroundColor: COLORS.main29,
    alignItems: 'center',
    justifyContent: 'center',
  },
  modeInTOn: {
    ...font.eb8,
    color: 'white',
  },
  modeInOff: {
    flex: 1,
    margin: getW(2),
    borderRadius: getW(4),
    alignItems: 'center',
    justifyContent: 'center',
  },
  modeInTOff: {
    ...font.b8,
    color: COLORS.main29,
  },
  busCont: {
    width: getW(320),
    height: getW(102),
    alignSelf: 'center',
    paddingTop: getW(16),
    paddingBottom: getW(14),
    paddingHorizontal: getW(20),
    justifyContent: 'space-between',
    backgroundColor: 'white',
    borderRadius: getW(12),
    ...SHADOW.xy04p10r8,
  },
});
