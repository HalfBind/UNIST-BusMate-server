import {HomeComp} from '@UI/homeComp';
import {LinearBGView, ListEmptyElem} from '@UI/share';
import {timeToNum} from '@_utils/converters';
import {isEmpty} from '@_utils/validation';
import API from '@apis/apis';
import {THICK_PADDING, getW} from '@constants/appUnits';
import {UserDataContext} from '@hooks/userDataContext';
import {FlatList_P} from '@platformPackage/gestureComponent';
import React, {useContext, useEffect, useState} from 'react';
import {TimePickerModal} from 'react-native-paper-dates';

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
        (a, b) => timeToNum(a.departureTime) - timeToNum(b.departureTime),
      );
      setState({
        loadState: isEmpty(filtered) ? 'empty' : 'loaded',
        busList: filtered,
      });
    }

    loadBusList();
  }, [dest, time, mode]);

  return (
    <LinearBGView>
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
        contentContainerStyle={{paddingBottom: getW(80)}}
        data={state.busList}
        renderItem={({item, index}) => {
          return (
            <HomeComp.BusInfo busInfo={item} style={{marginTop: getW(16)}} />
          );
        }}
        ListEmptyComponent={
          <ListEmptyElem
            description={'조건에 맞는 버스가 없습니다'}
            isLoading={state.loadState === 'loading'}
          />
        }
      />
      <HomeComp.AlarmListBtn
        style={{
          position: 'fixed',
          right: THICK_PADDING + getW(20),
          bottom: getW(20),
        }}
      />
      <TimePickerModal
        visible={modalVisible}
        onDismiss={() => setModalVisible(false)}
        onConfirm={({hours, minutes}) => {}}
      />
    </LinearBGView>
  );
}

export default HomePage;
