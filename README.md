# 🚌 UNIST 버스메이트

![Alt text](images/main.png)
UNIST 학내 구성원들의 버스 이용 어려움을 해소하기 위해, 기존의 복잡한 울산 버스정보를 간편히 이용할 수 있는 버스 유틸리티 서비스입니다.

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
				"routeNumber": "133",
				"routeDirection": "KKOTBAWI",
				"departureTime": "05:00",
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
				"routeNumber": "133",
				"routeDirection": "KKOTBAWI",
				"departureTime": "12:15",
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
				"routeNumber": "133",
				"routeDirection": "KKOTBAWI",
				"departureTime": "05:00",
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
				"routeNumber": "133",
				"routeDirection": "KKOTBAWI",
				"departureTime": "12:15",
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
        "routeNumber": "133",
        "routeDirection": "KKOTBAWI",
        "departureTime": "05:00",
        "destination": "GUYEONG_RI",
        "arrivalTime": "05:10"
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
        "routeNumber": "133",
        "routeDirection": "KKOTBAWI",
        "departureTime": "12:15",
        "destination": "GUYEONG_RI",
        "arrivalTime": "12:25"
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
        "routeNumber": "133",
        "routeDirection": "KKOTBAWI",
        "departureTime": "05:00",
        "destination": "GUYEONG_RI",
        "arrivalTime": "05:10"
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
        "routeNumber": "733",
        "routeDirection": "DEOKHA_GARAGE",
        "departureTime": "12:00",
        "destination": "GUYEONG_RI",
        "arrivalTime": "12:10"
    },
    {
        "routeNumber": "337",
        "routeDirection": "TAEWHA_RIVER",
        "departureTime": "12:00",
        "destination": "GUYEONG_RI",
        "arrivalTime": "12:10"
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
        "userName": "Gibeom",
        "days":["SUN"],
        "busInfo": {
            "routeNumber": "733",
            "routeDirection": "DEOKHA_GARAGE",
            "departureTime": "19:30",
            "destinations": [
                "GUYEONG_RI",   "ULSAN_UNIVERSITY","SINBOK_ROTARY",
                "SEONGNAM",
                "SAMSAN"
            ]
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
    "userName": "Gibeom",
    "days":["SUN"],
    "busInfo": {
        "routeNumber": "733",
        "routeDirection": "DEOKHA_GARAGE",
        "departureTime": "19:30",
        "destinations": [
            "GUYEONG_RI",
            "ULSAN_UNIVERSITY",
            "SINBOK_ROTARY",
            "SEONGNAM",
            "SAMSAN"
        ]
    }
}

```

- `DELETE /bookmarks/{bookmarkId}`

```bash
curl -X DELETE "http://localhost:8080/bookmarks/0"
```

Expected output:

- No content. If the bookmark is successfully deleted, return status 204.
