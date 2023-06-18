import {Horizon} from '@components/templates/defaultComps';
import {getW} from '@constants/appUnits';
import {DAYS} from '@constants/dataConfig';
import {COMMON_IC} from '@constants/imageMap';
import {Image_local} from '@platformPackage/Image';
import {TextInput_P} from '@platformPackage/gestureComponent';
import COLORS from '@styles/colors';
import font from '@styles/textStyle';
import {
  PressCallback,
  PressGoBack,
  PressNavigate,
} from '@userInteraction/pressAction';
import React from 'react';
import {Text, View} from 'react-native';

const DayToggle = ({onToggle, isOn, engDay}) => {
  return (
    <PressCallback
      style={{paddingVertical: getW(10), alignItems: 'center'}}
      onPress={onToggle}
      horizon>
      <Image_local
        source={isOn ? COMMON_IC.check_on : COMMON_IC.check_off}
        style={{width: getW(24), height: getW(24), marginRight: getW(6)}}
      />
      <Text style={[font.b16]}>{DAYS[engDay]}</Text>
    </PressCallback>
  );
};

export const AlarmComp = {
  Header: ({style}) => (
    <Horizon
      style={{
        height: getW(65),
        alignItems: 'center',
        backgroundColor: 'white',

        ...style,
      }}>
      <View horizon style={{paddingLeft: getW(18), alignItems: 'flex-start'}}>
        <PressGoBack>
          <Image_local
            style={{width: getW(24), height: getW(24)}}
            source={COMMON_IC.arrow_back}
          />
        </PressGoBack>
      </View>
      <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
        <Text style={[font.b20, {color: COLORS.gray48}]}>알림 예약 하기</Text>
      </View>
      <PressNavigate
        routeName={'AlarmList'}
        style={{alignItems: 'flex-end', paddingRight: getW(18)}}>
        <Image_local
          source={COMMON_IC.access_alarm}
          style={{width: getW(24), height: getW(24)}}
        />
      </PressNavigate>
    </Horizon>
  ),
  InfoCard: ({routeNumber, departureTime, style}) => (
    <Horizon
      style={{
        width: getW(300),
        height: getW(75),
        borderRadius: getW(10),
        borderWidth: 2,
        alignItems: 'center',
        justifyContent: 'spacegetin',
        backgroundColor: 'white',
        borderColor: COLORS.main,
        ...style,
      }}>
      <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
        <Text style={[font.r12, {color: COLORS.main}]}>노선</Text>
        <Text style={[font.b24, {color: COLORS.main29}]}>{routeNumber}</Text>
      </View>
      <View
        style={{
          width: 0,
          borderRightWidth: 2,
          borderColor: COLORS.main20P,
          height: getW(60),
          borderStyle: 'dashed',
        }}
      />
      <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
        <Text style={[font.r12, {color: COLORS.main}]}>출발 시간</Text>
        <Text style={[font.b24, {color: COLORS.main29}]}>{departureTime}</Text>
      </View>
    </Horizon>
  ),
  InputMinute: ({toggleDayUse, isDayUse, onTextChange}) => {
    return (
      <View
        style={{
          backgroundColor: 'white',
          paddingVertical: getW(18),
          paddingHorizontal: getW(18),
        }}>
        <Text style={[font.eb12]}>알림 시간</Text>
        <Horizon style={{alignSelf: 'center', alignItems: 'center'}}>
          <TextInput_P
            keyboardType="numeric"
            maxLength={2}
            onChangeText={text => onTextChange(text)}
            style={[
              font.b32,
              {
                paddingVertical: getW(11),
                width: getW(72),
                borderBottomWidth: 3,
                alignItems: 'center',
                textAlign: 'center',
              },
            ]}
          />
          <Text style={[font.b16]}>분 전</Text>
        </Horizon>
        <PressCallback
          horizon
          style={{alignSelf: 'flex-end', alignItems: 'center'}}
          onPress={toggleDayUse}>
          <Text
            style={[
              font.b12,
              {color: isDayUse ? COLORS.main : 'black', marginRight: getW(5)},
            ]}>
            요일 반복
          </Text>
          <Image_local
            source={isDayUse ? COMMON_IC.toggle_on : COMMON_IC.toggle_off}
            style={{width: getW(22), height: getW(22)}}
          />
        </PressCallback>
      </View>
    );
  },
  DaySelecter: ({toggleDay, isDayUse, dayList}) => {
    if (isDayUse) {
      return (
        <View
          style={{
            backgroundColor: 'white',
            paddingHorizontal: getW(18),
            paddingTop: getW(18),
            paddingBottom: getW(100),
            marginTop: getW(4),
          }}>
          <Text style={[font.eb12, {marginBottom: getW(14)}]}>반복 요일</Text>
          <View>
            {Object.keys(dayList).map(dayEng => (
              <DayToggle
                engDay={dayEng}
                isOn={dayList[dayEng].isSelected}
                onToggle={() => toggleDay(dayEng)}
              />
            ))}
          </View>
        </View>
      );
    } else {
      return null;
    }
  },
};
