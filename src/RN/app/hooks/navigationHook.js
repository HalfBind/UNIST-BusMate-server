// #region helperfunction

import {navigationConfig} from '@navigationConfigs/navigationConfig';
import {getFlatNavigationStructure} from '@navigationConfigs/navigationFunctions';
import {
  CommonActions,
  StackActions,
  createNavigationContainerRef,
  useNavigation,
  useNavigationState,
  useRoute,
} from '@react-navigation/native';
import {Platform} from 'react-native';

// helper functions for navigation function and hooks

export const _rootNavigationRef = createNavigationContainerRef();
const flatNavigationStructure = getFlatNavigationStructure(navigationConfig);

export const _getRootNavigationState = () => {
  return _rootNavigationRef.getRootState();
  // console.log('getRootState', _rootNavigationRef.getRootState());
};

const getCurPath = (curState, path) => {
  const routeInfo = curState.routes[curState.index];
  const childState = routeInfo.state;
  if (childState) {
    path.push(routeInfo.name);
    return getCurPath(childState, path);
  } else {
    return path;
  }
};

const getCurRoute = curState => {
  const curIndex = curState.index ?? 0;
  const newCurState = curState?.routes[curIndex]?.state;
  if (newCurState) {
    return getCurRoute(newCurState);
  } else {
    return curState.routes[curIndex];
  }
};

const getKeyWithName = (name, state) => {
  // console.log('now searching', state.name);
  if (state?.name === name) {
    return state.key;
  } else if (state?.state) {
    return getKeyWithName(name, state.state);
  } else if (state?.routes) {
    for (let i = 0; i < state.routes.length; i++) {
      const res = getKeyWithName(name, state.routes[i]);
      if (res) {
        return res;
      }
    }
    return false;
  } else {
    return false;
  }
};
const getKeyAndParamWithName = (name, state) => {
  // console.log('now searching', state.name);
  if (state?.name === name) {
    const key = state.key;
    const params = state.params ? state.params : {};

    return {key, params};
  } else if (state?.state) {
    return getKeyAndParamWithName(name, state.state);
  } else if (state?.routes) {
    for (let i = 0; i < state.routes.length; i++) {
      const res = getKeyAndParamWithName(name, state.routes[i]);
      if (res) {
        return res;
      }
    }
    return false;
  } else {
    return false;
  }
};
const getParamWithName = (name, state) => {
  // console.log('now searching', state.name);
  if (state?.name === name) {
    const params = state?.params;
    // if (params) {
    //   console.log('found params of ', name, ', :', params);
    // } else {
    //   console.log('found name ', name, 'but no params');
    // }
    return params;
  } else if (state?.state) {
    return getParamWithName(name, state.state);
  } else if (state?.routes) {
    for (let i = 0; i < state.routes.length; i++) {
      const res = getParamWithName(name, state.routes[i]);
      if (res) {
        return res;
      }
    }
    return false;
  } else {
    return false;
  }
};

const getTargetPath = (flatNavigationStructure, targetName, path) => {
  // console.log('parameter name path ', targetName, path);
  const parent = flatNavigationStructure[targetName].parent;
  // console.log('parent of ', targetName, ' : ', parent);
  if (parent !== 'RootStackNavigator') {
    path.unshift(parent);
    return getTargetPath(flatNavigationStructure, parent, path);
  } else {
    return path;
  }
};

const canBeInitialScreen = (flatNavigationStructure, screenName) => {
  //  console.log('check initial screen for ', screenName);
  const parent = flatNavigationStructure[screenName].parent;
  if (parent) {
    return !navigationConfig[parent]?.navigatorParams?.initialRouteName;
  } else {
    return true;
  }
};

function makeNavigateConfig(
  flatNavigationStructure,
  nameList,
  endObj,
  isInitial = false,
) {
  const firstTarget = nameList[0];
  nameList.shift();
  // console.log(
  //   'first name List input',
  //   nameList,
  //   'endObj : ',
  //   endObj,
  //   'is Initial : ',
  //   isInitial,
  // );
  let paramObj = endObj;
  if (nameList.length !== 0) {
    const nestObj = (keyList, obj) => {
      // console.log('key list input :', keyList);
      const newScreen = keyList.pop();
      const initial = !isInitial
        ? canBeInitialScreen(flatNavigationStructure, newScreen)
        : isInitial;
      const newObj = {screen: newScreen, params: obj, initial};
      // console.log('after key List :', keyList);
      if (keyList.length !== 0) {
        return nestObj(keyList, newObj);
      } else {
        return newObj;
      }
    };
    paramObj = nestObj(nameList, endObj);
  }
  return {
    screen: firstTarget,
    params: paramObj,
  };
}

// #endregion

const removeName = (navState, nameList) => {
  const {routes} = navState;
  let newRoutes = routes;
  if (routes) {
    newRoutes = routes.reduce((newRoutRes, innerRouteInfo, index) => {
      // console.log('👀👀👀cur Res : \n', newRoutRes);
      const {key, name} = innerRouteInfo;
      // console.log(
      //   '✅routes of :',
      //   navState.key,
      //   '\n info : ',
      //   routes,
      //   '\nnow seeing..',
      //   name,
      // );
      if (!nameList.includes(name)) {
        // console.log('💥found name!, ', name);
        let newInnerRouteInfo = innerRouteInfo;
        if (innerRouteInfo.routes) {
          newInnerRouteInfo = removeName(innerRouteInfo, nameList);
        }
        newRoutRes.push(newInnerRouteInfo);
      }
      return newRoutRes;
    }, []);
  }
  return {...navState, routes: newRoutes, stale: true};
};

function pushRouteToState(routeInfo, lastStack, screenName, params) {
  console.log('😥input nav state : ', JSON.stringify(routeInfo));
  const {name} = routeInfo;
  const navState = routeInfo.routes ? routeInfo : routeInfo.state;
  let {routes: newRoutes, index: nextIndex, routeNames} = navState;
  navState.stale = true;
  console.log('cur route ; ', name);
  if (name === lastStack) {
    console.log('yeah found : ', {name: screenName, params});

    // !routeNames.includes(screenName) && navState.routeNames.push(screenName);
    navState.routes.push({
      name: screenName,
      params: {...params},
    });
    navState.index++;
  } else {
    console.log('not found yet... : ', {name: screenName, params});
    navState.routes[nextIndex] = pushRouteToState(
      newRoutes[nextIndex],
      lastStack,
      screenName,
      params,
    );
  }
  return {...routeInfo, ...(name && {state: navState})};
}
function removeWithKey(routeInput, targetKey) {
  // console.log('😥input nav state : ', JSON.stringify(routeInput));
  const routeInfo = {...routeInput};
  const {key, name} = routeInfo;
  let navState = routeInfo?.routes ? routeInfo : routeInfo?.state;
  if (!navState) {
    return routeInfo;
  }
  let {routes, index: nextIndex, routeNames} = navState;
  let newRoutes = [...routes];
  navState.stale = true;
  console.log('cur route ; ', name);
  const keyList = routes.map(routeObj => routeObj.key);
  console.log('key list : ', keyList, 'target key : ', targetKey);
  if (keyList.includes(targetKey)) {
    console.log('yeah found : ');
    navState.routes = newRoutes.filter(
      childRoute => childRoute.key !== targetKey,
    );
    navState.index--;
  } else {
    console.log('not found... : ');
    navState.routes = newRoutes.map((childRoute, index) => {
      return removeWithKey(childRoute, targetKey);
    });
  }
  console.log('stale true? ', navState.stale);
  return {...routeInfo, ...(name && {state: navState})};
}

export const _useNavFunctions = () => {
  const _navState = useNavigationState(state => state);
  const _curNavigation = useNavigation();
  const _curRoute = useRoute();

  const FUNCTIONS = {
    _removeSpecificRoute: (nameList, key) => {
      try {
        const rootState = _getRootNavigationState();
        // console.log('root state', JSON.stringify(rootState));
        const newState = removeName({...rootState}, nameList);
        // console.log('new state : ', JSON.stringify(newState));
        _rootNavigationRef.resetRoot(newState);
      } catch (error) {
        console.log('error from remove specificRoute', error);
      }
    },
    _navigate: (
      name,
      param,
      isInitial = false,
      navigation = _curNavigation,
    ) => {
      try {
        const parentNavigator = navigation.getParent();
        const parentState = parentNavigator?.getState();
        const parentIndex = parentState?.index;
        const parentNameOfCurScreen = parentNavigator
          ? parentState.routes[parentIndex].name
          : 'RootStackNavigator';
        const parentNameOfTarget = flatNavigationStructure[name].parent;
        // console.log(
        //   'direct navigate ? ',
        //   parentNameOfCurScreen,
        //   flatNavigationStructure,
        // );
        if (parentNameOfCurScreen === parentNameOfTarget) {
          // console.log('direct navigating to ', name);
          navigation.navigate(name, param);
        } else {
          const rootState = _rootNavigationRef.current.getRootState();
          const initial = isInitial
            ? canBeInitialScreen(flatNavigationStructure, name)
            : isInitial;
          let curPath = getCurPath(rootState, []);
          let targetPath = getTargetPath(flatNavigationStructure, name, []);
          let navigatePath = [...targetPath];
          for (
            let index = 0;
            index < Math.min(curPath.length, targetPath.length) - 1;
            index++
          ) {
            if (curPath[index + 1] === targetPath[index + 1]) {
              navigatePath.shift();
            } else {
              // console.log('navigate path', navigatePath);
              break;
            }
          }
          const navigateObj = makeNavigateConfig(
            flatNavigationStructure,
            navigatePath,
            {
              screen: name,
              params: param,
              initial,
            },
            isInitial,
          );
          // console.log('navigationObj', JSON.stringify(navigateObj));
          const isRoot = !navigateObj.screen;
          isRoot
            ? _rootNavigationRef.navigate(
                navigateObj.params.screen,
                navigateObj.params.params,
              )
            : navigation.navigate(navigateObj.screen, navigateObj.params);
        }
      } catch (error) {
        console.log('errorfrom _navigate', error);
      }
    },
    _push: (name, param, isInitial = false, navigation = _curNavigation) => {
      try {
        const parentNavigator = navigation.getParent();
        const parentState = parentNavigator?.getState();
        const parentIndex = parentState?.index;
        const parentNameOfCurScreen = parentNavigator
          ? parentState.routes[parentIndex].name
          : 'RootStackNavigator';
        const parentNameOfTarget = flatNavigationStructure[name].parent;
        // console.log(
        //   'direct navigate ? ',
        //   parentNameOfCurScreen,
        //   parentNameOfTarget,
        // );
        if (parentNameOfCurScreen === parentNameOfTarget) {
          // console.log('direct navigating to ', name);
          navigation.push(name, param);
        } else {
          const rootState = _rootNavigationRef.current.getRootState();
          const initial = !isInitial
            ? canBeInitialScreen(flatNavigationStructure, name)
            : isInitial;
          // console.log('root state?', rootState);
          let curPath = getCurPath(rootState, []);
          let targetPath = getTargetPath(flatNavigationStructure, name, []);
          let navigatePath = [...targetPath];
          for (
            let index = 0;
            index < Math.min(curPath.length, targetPath.length) - 1;
            index++
          ) {
            if (curPath[index + 1] === targetPath[index + 1]) {
              navigatePath.shift();
            } else {
              //console.log('navigate path', navigatePath);
              break;
            }
          }
          // const lastStackName = navigatePath
          //   .filter(name => name.includes('StackNavigator'))
          //   .slice(-1)[0];
          // const newState = pushRouteToState(
          //   rootState,
          //   lastStackName,
          //   name,
          //   param,
          // );
          // console.log('📌new state!', JSON.stringify(newState));
          const navigateConfig = makeNavigateConfig(
            flatNavigationStructure,
            navigatePath,
            {
              screen: name,
              params: param,
              initial,
            },
            isInitial,
          );
          const isRoot = !navigateConfig.screen;
          isRoot
            ? _rootNavigationRef.navigate(
                navigateConfig.params.screen,
                navigateConfig.params.params,
              )
            : _rootNavigationRef.navigate(
                navigateConfig.screen,
                navigateConfig.params,
              );

          // _rootNavigationRef.resetRoot();
        }
        // console.log(
        //   'after state : ',
        //   JSON.stringify(_rootNavigationRef.getRootState()),
        // );
      } catch (error) {
        console.log('errorfrom _navigate', error);
      }
    },
    _navState,
    _curRoute,
    _curNavigation,
    _goBack: (navigation = _curNavigation) => {
      console.log('can goback?', navigation.canGoBack());
      const canGoBack = navigation.canGoBack();
      if (canGoBack) {
        navigation.goBack();
      } else {
        if (Platform.OS === 'web') {
          window.history.back();
        }
        return;
      }
    },
    _canGoBackExit: (navigation = _rootNavigationRef) => {
      return navigation.canGoBack();
    },
    _pop: (number = 1, navigation = _curNavigation) => {
      // console.log('can pop?', navigation.getState());
      navigation.dispatch(StackActions.pop(number));
    },
    _replace: (name, param, isInitial = false, navigation = _curNavigation) => {
      try {
        const parentNavigator = navigation.getParent();
        const parentState = parentNavigator?.getState();
        const parentIndex = parentState?.index;
        const parentNameOfCurScreen = parentNavigator
          ? parentState.routes[parentIndex].name
          : 'RootStackNavigator';
        const parentNameOfTarget = flatNavigationStructure[name].parent;
        // console.log(
        //   'direct replace ? ',
        //   parentNameOfCurScreen,
        //   parentNameOfTarget,
        // );
        if (parentNameOfCurScreen === parentNameOfTarget) {
          // console.log('direct navigating to ', name);
          navigation.dispatch(StackActions.replace(name, param));
        } else {
          const rootState = _rootNavigationRef.current.getRootState();
          const initial = !isInitial
            ? canBeInitialScreen(flatNavigationStructure, name)
            : isInitial;
          // console.log('root state?', rootState);
          let curPath = getCurPath(rootState, []);
          let targetPath = getTargetPath(flatNavigationStructure, name, []);
          let navigatePath = [...targetPath];
          // console.log('current path ', curPath);
          // console.log('target path', targetPath);
          for (
            let index = 0;
            index < Math.min(curPath.length, targetPath.length) - 1;
            index++
          ) {
            if (curPath[index + 1] === targetPath[index + 1]) {
              navigatePath.shift();
            } else {
              break;
            }
          }
          const navigateObj = makeNavigateConfig(
            flatNavigationStructure,
            navigatePath,
            {
              screen: name,
              params: param,
              initial,
            },
            isInitial,
          );
          // console.log('navigationObj', JSON.stringify(navigateObj));

          const navigateConfig = makeNavigateConfig(
            flatNavigationStructure,
            navigatePath,
            {
              screen: name,
              params: param,
              initial,
            },
            isInitial,
          );
          const isRoot = !navigateConfig.screen;
          navigation.pop();
          isRoot
            ? _rootNavigationRef.navigate(
                navigateConfig.params.screen,
                navigateConfig.params.params,
              )
            : _rootNavigationRef.navigate(
                navigateConfig.screen,
                navigateConfig.params,
              );
          // const targetKey = getCurRoute(rootState).key;
          // const isRoot = !navigateObj.screen;
          // isRoot
          //   ? _rootNavigationRef.dispatch(
          //       CommonActions.navigate(
          //         navigateObj.params.screen,
          //         navigateObj.params.params,
          //       ),
          //     )
          //   : _rootNavigationRef.dispatch(
          //       CommonActions.navigate(navigateObj.screen, navigateObj.params),
          //     );

          // const newState = removeWithKey(
          //   _rootNavigationRef.getState(),
          //   targetKey,
          // );

          // console.log('💥will reste state : ', JSON.stringify(newState));

          // _rootNavigationRef.dispatch(state => {
          //   console.log('👍before state : ', JSON.stringify(state));
          //   const newState = removeWithKey(state, getCurRoute(rootState).key);
          //   console.log('💥will reste state : ', JSON.stringify(newState));
          //   return CommonActions.reset(newState);
          // });
        }
      } catch (error) {
        console.log('errorfrom _replace', error);
      }
    },
    _setParams: (params, name = undefined, navigation = _curNavigation) => {
      let key;
      if (name) {
        const rootState = _rootNavigationRef.getRootState();
        key = getKeyWithName(name, rootState);
        if (!key) {
          console.log('theres no active route name "', name, '"');
          return;
        }
      }
      navigation.dispatch({
        ...CommonActions.setParams(params),
        source: key,
      });
      // console.log(
      //   'setting screen ',
      //   name,
      //   ' to param : ',
      //   params,
      //   'with Key :',
      //   key,
      // );
    },
    _getParamsOf: (name = undefined) => {
      if (!name) {
        // console.log('get params of cur screen :', _curRoute.name);
        return _curRoute.params;
      } else {
        const rootState = _rootNavigationRef.getRootState();
        const params = getParamWithName(name, rootState);
        return params;
      }
    },

    _getCurParam: (key = null) => {
      if (_curRoute.params) {
        if (!key) {
          return _curRoute.params;
        } else {
          if (_curRoute.params[key]) {
            return _curRoute.params[key];
          } else {
            // console.log('no match key for "', key, '"');
          }
        }
      } else {
        //console.log('no route params');
        return {};
      }
    },
    _getReloadParam: (key = null) => {
      if (_curRoute.params) {
        if (!key) {
          return _curRoute.params?.reload ?? {};
        } else {
          if (_curRoute.params[key]) {
            return _curRoute.params[key]?.reload ?? {};
          } else {
            // console.log('no match key for "', key, '"');
          }
        }
      } else {
        //console.log('no route params');
        return {};
      }
    },
  };

  return FUNCTIONS;
};
