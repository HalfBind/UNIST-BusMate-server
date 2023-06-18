import {AlarmComp} from '@UI/alarmComp';
import {LinearBGView} from '@UI/share';
import {isExist} from '@_utils/validation';
import API from '@apis/apis';
import {getW} from '@constants/appUnits';
import {DAYS} from '@constants/dataConfig';
import {_useNavFunctions} from '@hooks/navigationHook';
import {UserDataContext} from '@hooks/userDataContext';
import {useMyToast} from '@platformPackage/Toast';
import {ScrollView_P} from '@platformPackage/gestureComponent';
import {useRoute} from '@react-navigation/native';
import COLORS from '@styles/colors';
import font from '@styles/textStyle';
import {PressCallback} from '@userInteraction/pressAction';
import React, {useContext, useState} from 'react';
import {Text} from 'react-native';

function AddAlarmPage() {
  const {
    params: {routeNumber, busId, departureTime},
  } = useRoute();
  const {_goBack, _navigate} = _useNavFunctions();
  const {userName} = useContext(UserDataContext);
  const [controllState, setControllState] = useState({
    useDays: false,
    timeOffset: '10',
  });
  const {showDefaultToast} = useMyToast();

  const [daySelect, setDaySelect] = useState(
    Object.keys(DAYS).reduce((out, dayEng) => {
      out[dayEng] = {kor: DAYS[dayEng], isSelected: false};
      return out;
    }, {}),
  );

  const toggleDay = dayEng =>
    setDaySelect(prev => ({
      ...prev,
      [dayEng]: {kor: DAYS[dayEng], isSelected: !prev[dayEng].isSelected},
    }));

  const saveAlarm = async () => {
    try {
      if (isExist(userName)) {
        const res = await API.saveAlarm({
          busId: Number(busId),
          userName,
          days: Object.keys(daySelect).filter(
            dayKey => daySelect[dayKey].isSelected,
          ),
          timeOffset: !Number(controllState.timeOffset)
            ? 10
            : Number(controllState.timeOffset),
        });
        if (res.status === 201) {
          showDefaultToast('알림을 저장했습니다');
        }
        _goBack();
      } else {
        _navigate('Register');
      }
    } catch (error) {
      console.log('error from save Alarm : ', error);
    }
  };

  return (
    <LinearBGView>
      <AlarmComp.Header />
      <ScrollView_P>
        <AlarmComp.InfoCard
          routeNumber={routeNumber}
          departureTime={departureTime}
          style={{
            marginTop: getW(40),
            marginBottom: getW(20),
            alignSelf: 'center',
          }}
        />
        <AlarmComp.InputMinute
          curValue={controllState.timeOffset}
          onTextChange={text => {
            setControllState(prev => ({...prev, timeOffset: text}));
          }}
          toggleDayUse={() =>
            setControllState(prev => ({...prev, useDays: !prev.useDays}))
          }
          isDayUse={controllState.useDays}
        />
        <AlarmComp.DaySelecter
          toggleDay={toggleDay}
          isDayUse={controllState.useDays}
          dayList={daySelect}
        />
      </ScrollView_P>
      <PressCallback
        onPress={saveAlarm}
        style={{
          width: getW(330),
          position: 'fixed',
          bottom: getW(20),
          alignSelf: 'center',
          borderRadius: getW(10),
          backgroundColor: COLORS.main,
          alignItems: 'center',
          justifyContent: 'center',
          height: getW(60),
        }}>
        <Text style={[font.eb20, {color: 'white'}]}>저장하기</Text>
      </PressCallback>
    </LinearBGView>
  );
}

export default AddAlarmPage;
