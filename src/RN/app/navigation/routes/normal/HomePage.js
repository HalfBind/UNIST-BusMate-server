import {HomeComp} from '@UI/homeComp';
import API from '@apis/apis';
import {UserDataContext} from '@hooks/userDataContext';
import {FlatList_P} from '@platformPackage/gestureComponent';
import React, {useContext, useEffect, useState} from 'react';
import {Text, View} from 'react-native';

function HomePage() {
  const {dest, time, mode} = useContext(UserDataContext);
  const [state, setState] = useState({
    loadState: 'loading',
    busList: [],
  });

  useEffect(() => {
    async function loadBusList() {
      const {data: resList} = await API.getBusList({dest, time, mode});
      console.log('resLsit : ', resList);
      setState({loadState: 'loaded', busList: resList.slice(0, 10)});
    }
    //todo : 가까운 노선만 필터
    //todo : UNIST 방면 제외

    loadBusList();
  }, [dest, time, mode]);

  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <FlatList_P
        ListHeaderComponent={<HomeComp.Header homeState={{dest, time, mode}} />}
        data={state.busList}
        renderItem={({item, index}) => {
          return <HomeComp.BusInfo busInfo={item} />;
        }}
        ListEmptyComponent={<Text>조건에 맞는 버스가 없습니다</Text>}
      />
    </View>
  );
}

export default HomePage;
