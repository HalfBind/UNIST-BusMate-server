import {View_CenterModal} from '@components/templates/defaultComps';
import {WINDOW_HEIGHT, WINDOW_WIDTH} from '@constants/appUnits';
import {TEST_DEST_LIST} from '@constants/dataConfig';
import {UserDataContext} from '@hooks/userDataContext';
import {FlatList_P} from '@platformPackage/gestureComponent';
import {useNavigation} from '@react-navigation/native';
import {PressCallback} from '@userInteraction/pressAction';
import React, {useContext} from 'react';
import {Text, View} from 'react-native';

const Selector = ({value, onSelect}) => (
  <PressCallback onPress={() => onSelect(value)}>
    <Text>{value}</Text>
  </PressCallback>
);

function HomeSetDest() {
  const destList = TEST_DEST_LIST;
  const {setDest} = useContext(UserDataContext);
  const {goBack} = useNavigation();
  const onSelect = value => {
    setDest(value);
    goBack();
  };
  return (
    <View_CenterModal>
      <View
        style={{
          height: (1 / 2) * WINDOW_HEIGHT,
          width: (2 / 3) * WINDOW_WIDTH,
          backgroundColor: 'white',
        }}>
        <FlatList_P
          data={destList}
          renderItem={({item}) => {
            return <Selector value={item} onSelect={onSelect} />;
          }}
        />
      </View>
    </View_CenterModal>
  );
}

export default HomeSetDest;
