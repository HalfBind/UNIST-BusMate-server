import LinearGradient from 'react-native-linear-gradient';
import React from 'react';
import {ActivityIndicator, Text, View} from 'react-native';
import {WINDOW_HEIGHT} from '@constants/appUnits';
import font from '@styles/textStyle';
import COLORS from '@styles/colors';

export const LinearBGView = ({children, style}) => (
  <LinearGradient
    start={{x: 0, y: 0}}
    end={{x: 1, y: 1}}
    colors={['rgba(250, 250, 250, 1)', 'rgba(239, 246, 255, 1)']}
    style={{flex: 1, ...style}}>
    {children}
  </LinearGradient>
);

export const ListEmptyElem = ({description, isLoading}) => (
  <View
    style={{
      height: WINDOW_HEIGHT / 2,
      alignItems: 'center',
      justifyContent: 'center',
    }}>
    {!isLoading ? (
      <Text style={[font.b16, {color: '#868686'}]}>{description}</Text>
    ) : (
      <ActivityIndicator size={'large'} color={COLORS.main} />
    )}
  </View>
);
