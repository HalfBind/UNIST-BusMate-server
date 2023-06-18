# UNIST Bus Mate Server Application
## How to use

1. On the main screen, you can easily access the search button, destination-based search modal, and quickly view the fastest bus information at a glance.

2. By utilizing the destination-based search feature, you can enter your desired destination and easily access a list of buses that will take you there.

3. You can also perform searches based on your preferred departure time. Simply input your desired departure time, and the app will provide you with bus options that align with your schedule.

4. If you have a preferred bus that you frequently use, you can set up weekly notification reminders through the app. This way, you will receive timely alerts about the bus schedule and any relevant updates for your convenience.

5. To utilize this feature, you can create a personal account and log in using your unique ID. Once logged in, you will have access to set up weekly notification reminders for your preferred bus and enjoy the convenience of timely updates and alerts.

6. You can also customize the days of the week on which you would like to receive the notifications. When setting up the notification reminders, you will have the option to select specific days that are most convenient for you. This way, you can ensure that you receive alerts on the days that align with your regular bus travel schedule.

7. In the notification list screen, you can view and manage the notifications that you have registered. This screen will display a list of your active notifications, allowing you to easily review them. If you wish to remove any specific notification, you can select it from the list and choose the option to delete it. This gives you control over managing your notifications based on your preferences and needs.
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
