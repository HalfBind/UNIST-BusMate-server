import {View_CenterModal} from '@components/templates/defaultComps';
import {WINDOW_HEIGHT, WINDOW_WIDTH, getW} from '@constants/appUnits';
import {TEST_DEST_LIST} from '@constants/dataConfig';
import {UserDataContext} from '@hooks/userDataContext';
import {FlatList_P} from '@platformPackage/gestureComponent';
import {useNavigation} from '@react-navigation/native';
import font from '@styles/textStyle';
import {PressCallback} from '@userInteraction/pressAction';
import React, {useContext} from 'react';
import {Text, View} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';

const Selector = ({value, onSelect}) => (
  <PressCallback
    style={{
      paddingVertical: getW(9),
      alignItems: 'center',
      justifyContent: 'center',
    }}
    onPress={() => onSelect(value)}>
    <Text style={[font.b16]}>{value}</Text>
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
          contentContainerStyle={{paddingVertical: getW(40)}}
          data={destList}
          renderItem={({item}) => {
            return <Selector value={item} onSelect={onSelect} />;
          }}
        />
        <LinearGradient
          start={{x: 0, y: 0}}
          end={{x: 0, y: 1}}
          colors={['white', 'transparent']}
          style={{
            position: 'absolute',
            height: getW(50),
            top: 0,
            left: 0,
            right: 0,
          }}
        />
        <LinearGradient
          start={{x: 0, y: 1}}
          end={{x: 0, y: 0}}
          colors={['white', 'transparent']}
          style={{
            position: 'absolute',
            height: getW(50),
            bottom: 0,
            left: 0,
            right: 0,
          }}
        />
      </View>
    </View_CenterModal>
  );
}

export default HomeSetDest;
