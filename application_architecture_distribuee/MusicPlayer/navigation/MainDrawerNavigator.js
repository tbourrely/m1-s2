import React from "react";
import { createStackNavigator, createDrawerNavigator } from "react-navigation";

import TabBarIcon from "../components/TabBarIcon";
import HomeScreen from "../screens/HomeScreen";
import LibraryScreen from "../screens/LibraryScreen";

const HomeStack = createStackNavigator({
  Home: HomeScreen
});

HomeStack.navigationOptions = {
  drawerLabel: "Player",
  drawerIcon: ({ focused }) => <TabBarIcon focused={focused} name={"speaker"} />
};

const LinksStack = createStackNavigator({
  Links: LibraryScreen
});

LinksStack.navigationOptions = {
  drawerLabel: "Library",
  drawerIcon: ({ focused }) => (
    <TabBarIcon focused={focused} name={"library-music"} />
  )
};

export default createDrawerNavigator({
  HomeStack,
  LinksStack
});
