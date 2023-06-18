import {LinearBGView, ListEmptyElem} from '@UI/share';
import {isExist} from '@_utils/validation';
import API from '@apis/apis';
import {Horizon} from '@components/templates/defaultComps';
import {getW} from '@constants/appUnits';
import {BET_ENG_KOR, DAYS} from '@constants/dataConfig';
import {COMMON_IC} from '@constants/imageMap';
import {UserDataContext} from '@hooks/userDataContext';
import {Image_local} from '@platformPackage/Image';
import {FlatList_P} from '@platformPackage/gestureComponent';
import COLORS from '@styles/colors';
import SHADOW from '@styles/shadow';
import font from '@styles/textStyle';
import {PressCallback, PressGoBack} from '@userInteraction/pressAction';
import React, {useContext, useEffect, useState} from 'react';
import {Text, View} from 'react-native';

const DayTag = ({dayEng}) => (
  <View
    style={{
      width: getW(18),
      height: getW(18),
      borderRadius: getW(4),
      marginRight: getW(4),
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: '#C5DFFF',
    }}>
    <Text style={[font.b10, {color: COLORS.main29}]}>{DAYS[dayEng][0]}</Text>
  </View>
);

const AlarmComp = {
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
        <Text style={[font.b20, {color: COLORS.gray48}]}>알림 목록</Text>
      </View>
      <View style={{alignItems: 'flex-end', paddingRight: getW(18)}} />
    </Horizon>
  ),
  Card: ({alarmId, days, busInfo, timeOffset = 10, style, onRemoveAlarm}) => {
    const dayKeys = Object.keys(DAYS);
    const {routeNumber, routeDirection, departureTime} = busInfo;
    return (
      <Horizon
        style={{
          width: getW(320),
          backgroundColor: 'white',
          borderRadius: getW(10),
          ...SHADOW.xy02p1r6,
          paddingVertical: getW(16),
          paddingHorizontal: getW(20),
          justifyContent: 'space-between',
          alignSelf: 'center',
          marginVertical: getW(8),
          ...style,
        }}>
        <View>
          <Horizon>
            {days
              .sort((a, b) => dayKeys.indexOf(a) - dayKeys.indexOf(b))
              .map(dayEng => (
                <DayTag dayEng={dayEng} />
              ))}
          </Horizon>
          <Horizon style={{alignItems: 'center', marginTop: getW(12)}}>
            <Text style={[font.b30, {marginRight: getW(6)}]}>
              {routeNumber}
            </Text>
            <Text
              style={[
                font.b12,
                {color: '#838383'},
              ]}>{`(${BET_ENG_KOR[routeDirection]}방면)`}</Text>
          </Horizon>
          <Text style={[font.b16]}>{departureTime + ' 출발'}</Text>
        </View>
        <View style={{alignItems: 'flex-end'}}>
          <Text
            style={[font.b12, {color: COLORS.main29, marginBottom: getW(20)}]}>
            {timeOffset + '분 전에 알림'}
          </Text>
          <PressCallback
            onPress={() => {
              onRemoveAlarm(alarmId);
            }}>
            <Image_local
              source={COMMON_IC.trash}
              style={{width: getW(20), height: getW(20)}}
            />
          </PressCallback>
        </View>
      </Horizon>
    );
  },
};

function AlarmListPage() {
  const {userName, inited} = useContext(UserDataContext);
  const [state, setState] = useState({
    reloadToggle: true,
    loadState: 'loading',
    alarmList: [],
  });

  useEffect(() => {
    if (!inited) {
      return;
    }
    load();
    async function load() {
      if (isExist(userName)) {
        const {data: resList} = await API.getAlarmList({userName});
        setState({loadState: 'done', alarmList: resList});
      } else {
        setState(prev => ({...prev, loadState: 'empty'}));
      }
    }
  }, [userName, inited]);

  const removeAlarm = async alarmId => {
    await API.deleteAlarm({alarmId});
    setState(prev => ({...prev, reloadToggle: !prev.reloadToggle}));
  };

  return (
    <LinearBGView>
      <AlarmComp.Header />
      <FlatList_P
        contentContainerStyle={{paddingVertical: getW(24)}}
        ListEmptyComponent={
          <ListEmptyElem
            isLoading={state.loadState === 'loading'}
            description={'등록한 알림이 없어요'}
          />
        }
        data={state.alarmList}
        renderItem={({item}) => {
          return <AlarmComp.Card {...item} onRemoveAlarm={removeAlarm} />;
        }}
      />
    </LinearBGView>
  );
}

export default AlarmListPage;
