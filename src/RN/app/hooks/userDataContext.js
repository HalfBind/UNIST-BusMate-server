import {getTimeString} from '@_utils/converters';
import {isExist} from '@_utils/validation';
import {initialContext} from '@constants/dataConfig';
import AsyncStorage from '@react-native-async-storage/async-storage';
import {createContext, useEffect, useMemo, useReducer} from 'react';

const initialData = initialContext;

const reducer = (prevState, action) => {
  switch (action.type) {
    case 'INIT': {
      return {
        ...prevState,
        ...action.config,
        inited: true,
      };
    }
    case 'SET_USER': {
      return {
        ...prevState,
        userNmae: action.userName,
      };
    }
    case 'SET_DEST_QUERY': {
      return {
        ...prevState,
        dest: action.dest,
      };
    }
    case 'SET_TIME_QUERY': {
      return {
        ...prevState,
        time: action.time,
      };
    }
    case 'SET_MODE_QUERY': {
      return {
        ...prevState,
        mode: action.mode,
      };
    }
    case 'REFRESH_TIME': {
      return {
        ...prevState,
        time: getTimeString(new Date()),
      };
    }
    default: {
      console.log('UNDEFINED TYPE!');
      return {
        ...prevState,
      };
    }
  }
};

export const UserDataContext = createContext();
export function CreateUserDataContext(props) {
  const [state, dispatch] = useReducer(reducer, initialData);
  const actions = useMemo(() => ({
    setDest: dest => {
      dispatch({type: 'SET_DEST_QUERY', dest});
    },
    setTime: time => {
      dispatch({type: 'SET_TIME_QUERY', time});
    },
    setMode: mode => {
      dispatch({type: 'SET_MODE_QUERY', mode});
    },
    refreshTime: () => {
      dispatch({type: 'REFRESH_TIME'});
    },
    setUser: async userName => {
      await AsyncStorage.setItem('USER_NAME', userName);
      dispatch({type: 'SET_USER_NAME', userName});
    },
    init: config => {
      dispatch({type: 'INIT', config});
    },
  }));

  useEffect(() => {
    initUser();
    async function initUser() {
      const curUserName = await AsyncStorage.getItem('USER_NAME');
      if (isExist(curUserName)) {
        actions.init({userName: curUserName});
      } else {
        actions.init({});
      }
    }
  }, []);

  return (
    <UserDataContext.Provider value={{...state, ...actions}}>
      {props.children}
    </UserDataContext.Provider>
  );
}
