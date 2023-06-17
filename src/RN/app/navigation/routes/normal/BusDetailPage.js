import {useRoute} from '@react-navigation/native';
import React from 'react';
import {Text, View} from 'react-native';

function BusDetailPage() {
  const {params} = useRoute();
  const {routeNumber} = params;
  return (
    <View>
      <Text>{routeNumber + 'detal'}</Text>
    </View>
  );
}

export default BusDetailPage;
