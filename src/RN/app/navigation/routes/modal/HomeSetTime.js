import {View_CenterModal} from '@components/templates/defaultComps';
import {_useNavFunctions} from '@hooks/navigationHook';
import {UserDataContext} from '@hooks/userDataContext';
import React, {useContext} from 'react';
import {View} from 'react-native';
import {TimePickerModal} from 'react-native-paper-dates';

function HomeSetTime() {
  const {_goBack} = _useNavFunctions();
  const {setTime} = useContext(UserDataContext);
  return (
    <View_CenterModal>
      <View>
        <TimePickerModal
          visible={true}
          onDismiss={() => _goBack()}
          onConfirm={({hours, minutes}) => {
            setTime(hours + ':' + minutes);
            _goBack();
          }}
        />
      </View>
    </View_CenterModal>
  );
}

export default HomeSetTime;
