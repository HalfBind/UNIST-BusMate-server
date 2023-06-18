/**
 * @format
 */

import 'react-native-gesture-handler';
import './app/web/font.css';
import App from './App.web';
import {AppRegistry, LogBox} from 'react-native';

LogBox.ignoreLogs([
  'Non-serializable values were found in the navigation state',
]);
AppRegistry.registerComponent('App', () => App);
AppRegistry.runApplication('App', {rootTag: document.getElementById('root')});
