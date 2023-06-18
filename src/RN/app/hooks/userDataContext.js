import {getTimeString} from '@_utils/converters';
import {initialContext} from '@constants/dataConfig';
import {createContext, useMemo, useReducer} from 'react';

const initialData = initialContext;

const reducer = (prevState, action) => {
  switch (action.type) {
    case 'SIGN_IN': {
      console.log('try signin');
      break;
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
  }));

  return (
    <UserDataContext.Provider value={{...state, ...actions}}>
      {props.children}
    </UserDataContext.Provider>
  );
}
