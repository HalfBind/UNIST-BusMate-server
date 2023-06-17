import {getMinuteLeft} from '@_utils/converters';
import {DoNothing} from '@_utils/handling';
import {Horizon} from '@components/templates/defaultComps';
import {getW} from '@constants/appUnits';
import {COMMON_IC} from '@constants/imageMap';
import {Image_local} from '@platformPackage/Image';
import COLORS from '@styles/colors';
import SHADOW from '@styles/shadow';
import font from '@styles/textStyle';
import {PressCallback, PressNavigate} from '@userInteraction/pressAction';
import React from 'react';
import {StyleSheet, Text, View} from 'react-native';

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
        <Horizon>
          <PressNavigate horizon routeName={'HomeSetDest'} style={ST.destBtn}>
            <View>
              <Text>목적지</Text>
              <Text>{dest}</Text>
            </View>
            <Image_local
              source={COMMON_IC.arrow_down}
              style={{width: getW(24), height: getW(24)}}
            />
          </PressNavigate>
          <View>
            <PressNavigate horizon routeName={'HomeSetTime'}>
              <Text>{time}</Text>
              <Image_local
                source={COMMON_IC.drop_down}
                style={{width: getW(24), height: getW(24)}}
              />
            </PressNavigate>
            <Text style={font.eb8}>{mode === 'leave' ? '이후' : '이전'}</Text>
            <ModeBtn curMode={mode} onSelect={onModeSelect} />
          </View>
          <View
            style={{
              height: getW(44),
              width: getW(2),
              backgroundColor: COLORS.main10P,
            }}
          />
          <PressCallback onPress={onRefresh}>
            <Image_local
              style={{width: getW(24), height: getW(24)}}
              source={COMMON_IC.refresh}
              tint={COLORS.main29}
            />
          </PressCallback>
        </Horizon>
        <View
          style={{
            width: '100%',
            height: getW(2),
            backgroundColor: COLORS.main20P,
          }}
        />
      </View>
    );
  },
  BusInfo: ({busInfo}) => {
    const {routeNumber, routeDirection, departureTime, arrivalTime} = busInfo;
    return (
      <View>
        <Horizon style={{justifyContent: 'space-between'}}>
          <Horizon>
            <Text>{routeNumber}</Text>
            <Text>{routeDirection + ' 방면'}</Text>
          </Horizon>
          <View>
            <Text>{getMinuteLeft(departureTime) + '분 후 출발'}</Text>
            <Text>{arrivalTime + '도착'}</Text>
          </View>
        </Horizon>
        <Horizon style={{justifyContent: 'space-between'}}>
          <PressNavigate
            horizon
            routeName={'BusDetail'}
            extraParam={{routeNumber}}>
            <Image_local
              source={COMMON_IC.time_table}
              style={{
                width: getW(16),
                height: getW(16),
              }}
            />
            <Text>시간표</Text>
          </PressNavigate>
          <PressNavigate
            horizon
            routeName={'AddAlarm'}
            extraParam={{routeNumber}}>
            <Image_local
              source={COMMON_IC.add_alarm}
              style={{
                width: getW(16),
                height: getW(16),
              }}
            />
            <Text>알림 예약</Text>
          </PressNavigate>
        </Horizon>
      </View>
    );
  },
};

const ST = StyleSheet.create({
  destBtn: {
    width: getW(150),
    height: getW(60),
    paddingLeft: getW(12),
    paddingVertical: getW(12),
    paddingRight: getW(6),
    flexDirection: 'row',
    backgroundColor: COLORS.gradBlue,
    borderRadius: getW(12),
    ...SHADOW.homeDestBtn,
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
    alignSelf: 'center',
    paddingVertical: getW(20),
    paddingTop: getW(16),
    paddingBottom: getW(14),
    backgroundColor: 'white',
  },
});
