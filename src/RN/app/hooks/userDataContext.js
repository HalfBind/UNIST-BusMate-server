import {createContext, useMemo, useReducer} from 'react';

const initialData = {
  inited: false,
  isSigned: false,
  userName: null,
};

const reducer = (prevState, action) => {
  switch (action.type) {
    case 'SIGN_IN': {
      console.log('try signin');
      break;
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
  const actions = useMemo(() => {});

  return (
    <UserDataContext.Provider value={{...state, ...actions}}>
      {props.children}
    </UserDataContext.Provider>
  );
}
