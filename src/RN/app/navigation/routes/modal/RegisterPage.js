import {Horizon, View_CenterModal} from '@components/templates/defaultComps';
import {WINDOW_WIDTH, getW} from '@constants/appUnits';
import {_useNavFunctions} from '@hooks/navigationHook';
import {UserDataContext} from '@hooks/userDataContext';
import {useMyToast} from '@platformPackage/Toast';
import {TextInput_P} from '@platformPackage/gestureComponent';
import COLORS from '@styles/colors';
import font from '@styles/textStyle';
import {PressCallback, PressGoBack} from '@userInteraction/pressAction';
import React, {useContext, useState} from 'react';
import {Text, View} from 'react-native';

function RegisterPage() {
  const [newName, setNewName] = useState('');
  const {_goBack} = _useNavFunctions();

  const {setUser} = useContext(UserDataContext);
  const {showDefaultToast} = useMyToast();
  const setUserName = async () => {
    await setUser(newName);
    showDefaultToast('ID를 등록했습니다');
    _goBack();
  };
  return (
    <View_CenterModal>
      <View
        style={{
          backgroundColor: 'white',
          paddingTop: getW(14),
          width: (3 / 4) * WINDOW_WIDTH,
          alignItems: 'center',
          borderRadius: getW(10),
        }}>
        <Text style={[font.b12, {marginBottom: getW(30)}]}>
          알림을 저장하시려면 ID를 등록해 주세요
        </Text>
        <TextInput_P
          onChangeText={text => setNewName(text)}
          placeholder="10글자 이내로 입력해 주세요"
          style={{
            width: getW(198),
            height: getW(37),
            alignItems: 'center',
            borderRadius: getW(4),
            borderWidth: 2,
            marginBottom: getW(45),
            paddingHorizontal: getW(10),
            ...font.b12,
          }}
          placeholderTextColor={'#c3c3c3'}
        />
        <Horizon
          style={{width: '100%', borderTopWidth: 1, borderColor: '#EDEDED'}}>
          <PressCallback
            onPress={setUserName}
            style={{
              alignItems: 'center',
              justifyContent: 'center',
              flex: 1,
              paddingVertical: getW(12),
            }}>
            <Text style={[font.bold16, {color: COLORS.main}]}>등록하기</Text>
          </PressCallback>
          <View
            style={{width: 1, height: '100%', backgroundColor: '#EDEDED'}}
          />
          <PressGoBack
            style={{
              alignItems: 'center',
              justifyContent: 'center',
              flex: 1,
              paddingVertical: getW(12),
            }}>
            <Text style={[font.b16, {color: COLORS.red}]}>취소</Text>
          </PressGoBack>
        </Horizon>
      </View>
    </View_CenterModal>
  );
}

export default RegisterPage;
