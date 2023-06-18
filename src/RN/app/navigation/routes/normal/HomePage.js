import {HomeComp} from '@UI/homeComp';
import {isEmpty} from '@_utils/validation';
import API from '@apis/apis';
import {getW} from '@constants/appUnits';
import {UserDataContext} from '@hooks/userDataContext';
import {FlatList_P} from '@platformPackage/gestureComponent';
import COLORS from '@styles/colors';
import React, {useContext, useEffect, useState} from 'react';
import {ActivityIndicator, Text, View} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import {TimePickerModal} from 'react-native-paper-dates';

//todo : 가까운 노선만 필터
//todo : UNIST 방면 제외
//todo : refresh
//todo : empty page

function HomePage() {
  const {dest, time, mode, refreshTime, setMode} = useContext(UserDataContext);
  const [state, setState] = useState({
    loadState: 'loading',
    busList: [],
  });
  const [modalVisible, setModalVisible] = useState(false);

  useEffect(() => {
    async function loadBusList() {
      const {data: resList} = await API.getBusList({dest, time, mode});

      const dict = {};
      resList.map(curInfo => {
        const {routeNumber} = curInfo;
        if (!dict[routeNumber]) {
          dict[routeNumber] = curInfo;
        }
      });
      const filtered = Object.values(dict).sort(
        (a, b) => a.departureTime - b.departureTime,
      );
      console.log('resLsit : ', filtered);
      setState({
        loadState: isEmpty(filtered) ? 'empty' : 'loaded',
        busList: filtered,
      });
    }

    loadBusList();
  }, [dest, time, mode]);

  return (
    <LinearGradient
      start={{x: 0, y: 0}}
      end={{x: 1, y: 1}}
      colors={['rgba(250, 250, 250, 1)', 'rgba(239, 246, 255, 1)']}
      style={{flex: 1, backgroundColor: 'white'}}>
      <FlatList_P
        stickyHeaderIndices={[0]}
        ListHeaderComponent={
          <HomeComp.Header
            openTimePicker={() => setModalVisible(true)}
            onModeSelect={newMode => {
              setMode(newMode);
            }}
            onRefresh={refreshTime}
            homeState={{dest, time, mode}}
          />
        }
        contentContainerStyle={{paddingBottom: getW(50)}}
        data={state.busList}
        renderItem={({item, index}) => {
          return (
            <HomeComp.BusInfo busInfo={item} style={{marginTop: getW(16)}} />
          );
        }}
        ListEmptyComponent={
          state.loadState === 'empty' ? (
            <Text>조건에 맞는 버스가 없습니다</Text>
          ) : (
            <View>
              <ActivityIndicator size={'large'} color={COLORS.main} />
            </View>
          )
        }
      />
      <HomeComp.AddAlarmBtn
        style={{position: 'fixed', right: getW(20), bottom: getW(20)}}
      />
      <TimePickerModal
        visible={modalVisible}
        onDismiss={() => setModalVisible(false)}
        onConfirm={({hours, minutes}) => {}}
      />
    </LinearGradient>
  );
}

export default HomePage;
