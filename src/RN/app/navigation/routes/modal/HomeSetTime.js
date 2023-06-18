import {View_CenterModal} from '@components/templates/defaultComps';
import {UserDataContext} from '@hooks/userDataContext';
import {useNavigation} from '@react-navigation/native';
import React, {useContext} from 'react';
import {View} from 'react-native';
import {TimePickerModal} from 'react-native-paper-dates';

function HomeSetTime() {
  const {goBack} = useNavigation();
  const {setTime} = useContext(UserDataContext);
  return (
    <View_CenterModal>
      <View>
        <TimePickerModal
          visible={true}
          onDismiss={() => goBack()}
          onConfirm={({hours, minutes}) => {
            setTime(hours + ':' + minutes);
            goBack();
          }}
        />
      </View>
    </View_CenterModal>
  );
}

export default HomeSetTime;
