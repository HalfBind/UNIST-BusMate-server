# UNIST Bus Mate Server Application
## How to use

![스크린샷 2023-06-18 오후 7.44.22.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d01e53f0-7b42-4787-b0a6-087a5713348d/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-06-18_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_7.44.22.png)

1. On the main screen, you can easily access the search button, destination-based search modal, and quickly view the fastest bus information at a glance.

![스크린샷 2023-06-18 오후 7.45.40.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1e440e0f-b60d-415d-98f9-e3f9564a7b9f/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-06-18_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_7.45.40.png)

1. By utilizing the destination-based search feature, you can enter your desired destination and easily access a list of buses that will take you there.

![스크린샷 2023-06-18 오후 7.46.20.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/db6b1ff3-7412-49a7-ba9b-079da9829ea2/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-06-18_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_7.46.20.png)

1. You can also perform searches based on your preferred departure time. Simply input your desired departure time, and the app will provide you with bus options that align with your schedule.

![스크린샷 2023-06-18 오후 7.49.39.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d081412f-7bd2-4de3-980d-758e39b831dc/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-06-18_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_7.49.39.png)

1. If you have a preferred bus that you frequently use, you can set up weekly notification reminders through the app. This way, you will receive timely alerts about the bus schedule and any relevant updates for your convenience.

![스크린샷 2023-06-18 오후 7.50.01.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/544b01c9-34f7-48dc-a8f6-65fd493d3433/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-06-18_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_7.50.01.png)

1. To utilize this feature, you can create a personal account and log in using your unique ID. Once logged in, you will have access to set up weekly notification reminders for your preferred bus and enjoy the convenience of timely updates and alerts.

![스크린샷 2023-06-18 오후 7.50.52.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6b1e22f7-b2f9-4581-a5b2-b3682f33df5f/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-06-18_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_7.50.52.png)

1. You can also customize the days of the week on which you would like to receive the notifications. When setting up the notification reminders, you will have the option to select specific days that are most convenient for you. This way, you can ensure that you receive alerts on the days that align with your regular bus travel schedule.

![스크린샷 2023-06-18 오후 7.51.22.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c1e0463c-83d2-4af8-ad19-e0b405dfb4bf/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-06-18_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_7.51.22.png)

1. In the notification list screen, you can view and manage the notifications that you have registered. This screen will display a list of your active notifications, allowing you to easily review them. If you wish to remove any specific notification, you can select it from the list and choose the option to delete it. This gives you control over managing your notifications based on your preferences and needs.
## Features

### Bus

This feature provides several functionalities related to bus information.

- Route number-based bus information search
- Bus information search based on route number and departure time
- Bus information search based on route number and direction
- Bus information search based on route number, direction, and departure time

### Destination Info

This feature provides several functionalities related to bus arrival information.

- Search bus arrival information based on destination
- Search bus arrival information based on destination and departure time
- Search bus arrival information based on destination and desired arrival time
- Search bus arrival information based on destination, desired arrival time, and departure time

### Bookmark

This feature provides several functionalities for bus bookmarking.

- Create a bookmark
- Retrieve bookmarks
- Delete a bookmark

## REST APIs

### Bus

- `GET /buses/{routeNumber}`

  Retrieves bus information based on the route number.

- `GET /buses/{routeNumber}?departureTime={departureTime}`

  Retrieves bus information based on the route number and departure time.

- `GET /buses/{routeNumber}/{routeDirection}`

  Retrieves bus information based on the route number and direction.

- `GET /buses/{routeNumber}/{routeDirection}?departureTime={departureTime}`

  Retrieves bus information based on the route number, direction, and departure time.


### Destination Info

- `GET /destinationInfo/{destination}`

  Retrieves bus arrival information based on the destination.

- `GET /destinationInfo/{destination}?departureTime={departureTime}`

  Retrieves bus arrival information based on the destination and departure time.

- `GET /destinationInfo/{destination}/until/{arrivalTime}`

  Retrieves bus arrival information based on the destination and desired arrival time.

- `GET /destinationInfo/{destination}/until/{arrivalTime}?departureTime={departureTime}`

  Retrieves bus arrival information based on the destination, desired arrival time, and departure time.


### Bookmark

- `GET /bookmarks/{userName}`

  Retrieves bookmarks for the specified user.

- `POST /bookmarks`

  Creates a new bookmark. (Requires a JSON payload containing `busId`, `userName`, and `days`)

- `DELETE /bookmarks/{bookmarkId}`

  Deletes the specified bookmark.


## Examples of API usage (with curl)

### Bus

- `GET /buses/{routeNumber}`

```bash
curl -X GET "http://localhost:8080/buses/133"
```

Expected output:

```bash
[
		{
				"routeNumber":"133",
				"routeDirection":"KKOTBAWI",
				"departureTime":"05:00",
				"destinations":["GUYEONG_RI", "ULSAN_TERMINAL"]
		}, ...
]
```

- `GET /buses/{routeNumber}?departureTime={departureTime}`

```bash
curl -X GET "http://localhost:8080/buses/133?departureTime=12:00"
```

Expected output:

```bash
[
		{
				"routeNumber":"133",
				"routeDirection":"KKOTBAWI",
				"departureTime":"12:15",
				"destinations":["GUYEONG_RI", "ULSAN_TERMINAL"]
		}, ...
]
```

- `GET /buses/{routeNumber}/{routeDirection}`

```bash
curl -X GET "http://localhost:8080/buses/133/KKOTBAWI"
```

Expected output:

```bash
[
		{
				"routeNumber":"133",
				"routeDirection":"KKOTBAWI",
				"departureTime":"05:00",
				"destinations":["GUYEONG_RI", "ULSAN_TERMINAL"]
		}, ...
]
```

- `GET /buses/{routeNumber}/{routeDirection}?departureTime={departureTime}`

```bash
curl -X GET "http://localhost:8080/buses/133/KKOTBAWI?departureTime=12:00"
```

Expected output:

```bash
[
		{
				"routeNumber":"133",
				"routeDirection":"KKOTBAWI",
				"departureTime":"12:15",
				"destinations":["GUYEONG_RI", "ULSAN_TERMINAL"]
		}, ...
]
```

### Destination Information

- `GET /destinationInfo/{destination}`

```bash
curl -X GET "http://localhost:8080/destinationInfos/GUYEONG_RI"
```

Expected output:

```bash
[
    {
        "routeNumber":"133",
        "routeDirection":"KKOTBAWI",
        "departureTime":"05:00",
        "destination":"GUYEONG_RI",
        "arrivalTime":"05:10"
    }, ...
]
```

- `GET /destinationInfo/{destination}?departureTime={departureTime}`

```bash
curl -X GET "http://localhost:8080/destinationInfos/GUYEONG_RI?departureTime=12:00"
```

Expected output:

```bash
[
    {
        "routeNumber":"133",
        "routeDirection":"KKOTBAWI",
        "departureTime":"12:15",
        "destination":"GUYEONG_RI",
        "arrivalTime":"12:25"
    }, ...
]
```

- `GET /destinationInfo/{destination}/until/{arrivalTime}`

```bash
curl -X GET "http://localhost:8080/destinationInfos/GUYEONG_RI/until/12:16"
```

Expected output:

```bash
[
    {
        "routeNumber":"133",
        "routeDirection":"KKOTBAWI",
        "departureTime":"05:00",
        "destination":"GUYEONG_RI",
        "arrivalTime":"05:10"
    }, ...
]
```

- `GET /destinationInfo/{destination}/until/{arrivalTime}?departureTime={departureTime}`

```bash
curl -X GET "http://localhost:8080/destinationInfos/GUYEONG_RI/until/12:16?departureTime=12:00"
```

Expected output:

```bash
[
    {
        "routeNumber":"733",
        "routeDirection":"DEOKHA_GARAGE",
        "departureTime":"12:00",
        "destination":"GUYEONG_RI",
        "arrivalTime":"12:10"
    }, 
		{
        "routeNumber":"337",
        "routeDirection":"TAEWHA_RIVER",
        "departureTime":"12:00",
        "destination":"GUYEONG_RI",
        "arrivalTime":"12:10"
    }
]
```

### Bookmark

- `GET /bookmarks/{userName}`

```bash
curl -X GET "http://localhost:8080/bookmarks/Gibeom"
```

Expected output: (If the target bookmark exists. i. e. After run example of POST method)

```bash
[
	{
    "userName":"Gibeom",
    "days":["SUN"],
    "busInfo": {
        "routeNumber":"733",
        "routeDirection":"DEOKHA_GARAGE",
        "departureTime":"19:30",
        "destinations":["GUYEONG_RI","ULSAN_UNIVERSITY","SINBOK_ROTARY","SEONGNAM","SAMSAN"]
    }
}
]
```

If bookmark not exists, return status 404.

- `POST /bookmarks`

```bash
curl -X POST "http://localhost:8080/bookmarks" \
		-H "Content-Type: application/json" \
		-d '{"busId": 124, "userName": "Gibeom", "days": ["SUN"]}'
```

Expected output:

```bash
{
    "userName":"Gibeom",
    "days":["SUN"],
    "busInfo": {
        "routeNumber":"733",
        "routeDirection":"DEOKHA_GARAGE",
        "departureTime":"19:30",
        "destinations":["GUYEONG_RI","ULSAN_UNIVERSITY","SINBOK_ROTARY","SEONGNAM","SAMSAN"]
    }
}
```

- `DELETE /bookmarks/{bookmarkId}`

```bash
curl -X DELETE "http://localhost:8080/bookmarks/0"
```

Expected output:

- No content. If the bookmark is successfully deleted, return status 204.
