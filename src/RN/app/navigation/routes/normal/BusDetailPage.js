import {DetailComp} from '@UI/detailComp';
import {HomeComp} from '@UI/homeComp';
import {LinearBGView, ListEmptyElem} from '@UI/share';
import {getMinuteLeft} from '@_utils/converters';
import {isEmpty} from '@_utils/validation';
import API from '@apis/apis';
import {THICK_PADDING, getW} from '@constants/appUnits';
import {initialBusDetail} from '@constants/dataConfig';
import {FlatList_P} from '@platformPackage/gestureComponent';
import {useRoute} from '@react-navigation/native';
import SHADOW from '@styles/shadow';
import React, {useEffect, useState} from 'react';

function BusDetailPage() {
  const {
    params: {routeNumber, routeDirection},
  } = useRoute();
  const [state, setState] = useState(initialBusDetail);
  useEffect(() => {
    async function loadBusList() {
      const {data: resList} = await API.getTimeTable({
        routeNumber,
        routeDirection,
      });
      if (isEmpty(resList)) {
        setState(prev => ({...prev, loadState: 'empty'}));
      } else {
        let newState = initialBusDetail;
        let lastDiff = 0;
        resList.map((busInfo, index) => {
          let type = 'PAST';
          if (index === 0) {
            newState.destinations = busInfo.destinations;
            newState.startTime = busInfo.departureTime;
          }
          let timeLeft = getMinuteLeft(busInfo.departureTime);
          if (lastDiff < 0 && timeLeft >= 0) {
            type = 'NEAR';
          } else if (timeLeft < 0) {
            type = 'PAST';
          } else {
            type = 'LEFT';
          }
          lastDiff = timeLeft;
          newState.timeList.push({time: busInfo.departureTime, type, timeLeft});
          if (index === resList.length - 1) {
            newState.endTime = busInfo.departureTime;
          }
        });
        setState({loadState: 'done', ...newState});
      }
    }
    try {
      loadBusList();
    } catch (error) {
      console.log('error from load bus List');
    }
  }, [routeNumber]);

  return (
    <LinearBGView>
      <DetailComp.Header
        routeNumber={routeNumber}
        routeDirection={routeDirection}
        style={{marginBottom: getW(4), ...SHADOW.xy02p1r6}}
      />
      <DetailComp.DestInfo
        destinations={state.destinations}
        style={{...SHADOW.xy02p1r6}}
      />
      <FlatList_P
        ListHeaderComponent={
          <DetailComp.FirstEndInfo
            style={{paddingTop: getW(30), paddingBottom: getW(26)}}
            firstTime={state.startTime}
            endTime={state.endTime}
          />
        }
        ListEmptyComponent={
          <ListEmptyElem
            decscription={'잘못된 버스 노선을 입력하셨습니다'}
            isLoading={state.loadState === 'loading'}
          />
        }
        data={state.timeList}
        renderItem={({item}) => {
          return <DetailComp.TimeElem timeInfo={item} />;
        }}
      />
      <HomeComp.AlarmListBtn
        style={{
          position: 'fixed',
          right: THICK_PADDING + getW(20),
          bottom: getW(20),
        }}
      />
    </LinearBGView>
  );
}

export default BusDetailPage;
