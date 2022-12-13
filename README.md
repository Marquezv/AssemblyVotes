# Assembly Votes🗳️ - In building
## API to creating voting session

## Desenvolved with:
    - SpringBoot 3.0.0
    - Maven 3.8.6
    - Java 18


## About
Assembly Votes is an API under development, focused on facilitating the creation of voting sessions in polls created by users.


- Timer for start end final session
- Easy creation of users and surveys
- easy-to-use API

## In constrution :construction:
- SessionStatus - Waiting ( Session waiting all users for start votation)
- Improvements to the log


## Start Server 🌐

### Download 📥

```sh
git clone git@github.com:Marquezv/AssemblyVotes.git
```


 - In the project folder
```sh
cd assemblyVotes
```
 - Run
```sh
mvn spring-boot:run
```
### Config
 - Server start with local config (application-local.properties)
    - Main route: http://localhost:8081/api/v1/
    - Port: 8081
    - H2DB: http://localhost:8081/api/v1/h2-console
        - use: sa
        - password: [none]
    - Testing persistense models with LocalConfig(assemblyVotes/config/LocalConfig)

- Server start with prod config (application-prod.properties)
 - Main route: http://localhost:8080/api/v1/
    - Port: 8080
    - H2DB: http://localhost:8080/api/v1/h2-console
        - use: sa
        - password: [none]
    - Data pesistence in H2 

# Project 🚧
## This project has the MVC pattern, having controllers, services and repositories following the demand of the object

 
    ├── commons                   # Tools and StatusEnuns
    ├── config                    # Docs for configuration and LocalConfig for this test
    ├── domain                     # Entity, ResponseDTOs, RequestDTOs
    ├── exceptions                    # Exceptions trataments and config for this
    ├── repository                   # Interfaces for executing querys
    ├── resource                   # "Resources" - Routes of objects
    ├── service                   # Executing logical business rules
    |
    └── AssemblyVotesApplication.java       # Main Class

# Routes ⛕ - Let's start

## First [create a user](#Create-User)
## Ask your  question for the [survey](#Create-Survey)(yes and no)
## [Create a session](#Create-Session) to see the opinion of other users
## finally, the most important moment [vote](#VOTE)
# USERS

### Create User
| Method | Route | 
| ------------- | ------------- |
|POST           | /users | VOID

Insert new User with diferent and valid cpf

    "username" : "Lua",
    "cpf" : "140.726.100-20"


### FindBy UserID
| Method | Route | 
| ------------- | ------------- |
|GET            | /users/{ID} |

Find User with ID -- Return:

    {
    "user_id": 1,
    "username": "Vini",
    "created_on": "12/12/2022 19:48:37",
    "_links": {
        "self": {
            "href": "http://localhost:8081/api/v1/users/1"
        },
        "collection": {
            "href": "http://localhost:8081/api/v1/users"
        }
    }


    
### FindAll Users
| Method | Route | 
| ------------- | ------------- |
|GET            | /users |

Find all users registered users -- Return

    "_embedded": {
        "users": [
            {
                "user_id": 1,
                "username": "Vini",
                "created_on": "12/12/2022 19:37:58",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/v1/users/1"
                    },
                    "collection": {
                        "href": "http://localhost:8081/api/v1/users"
                    }
                }
            }, ...  


# SURVEYS
### Create Survey
| Method | Route | 
| ------------- | ------------- |
|POST           | /surveys | VOID

Insert your question 

    "description" : "Should we renovate the playground?",
    "user_id": "1"

### FindBy SurveyID
| Method | Route | 
| ------------- | ------------- |
|GET            | /surveys/{ID} |

Find Survey with ID -- Return:

    {
    "survey_id": 1,
    "user_id": 1,
    "userResponse": {
        "user_id": 1,
        "username": "Vini",
        "created_on": "12/12/2022 20:27:26",
        "_links": {
            "users": {
                "href": "http://localhost:8081/api/v1/users/1"
            }
        }
    },
    "description": "Should we renovate the playground?",
    "created_on": "12/12/2022 20:27:26",
    "_links": {
        "self": {
            "href": "http://localhost:8081/api/v1/surveys/1"
        },
        "collection": [
            {
                "href": "http://localhost:8081/api/v1/surveys"
            },
            {
                "href": "http://localhost:8081/api/v1/sessions/surveys/1"
            }
        ]
    }
}


    
### FindAll Surveys
| Method | Route | 
| ------------- | ------------- |
|GET            | /surveys |

Find all surveys -- Return

    "_embedded": {
        "surveys": [
            {
                "survey_id": 1,
                "user_id": 1,
                "userResponse": {
                    "user_id": 1,
                    "username": "Vini",
                    "created_on": "12/12/2022 20:27:26",
                    "_links": {
                        "users": {
                            "href": "http://localhost:8081/api/v1/users/1"
                        }
                    }
                },
                "description": "Should we renovate the playground?",
                "created_on": "12/12/2022 20:27:26",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/v1/surveys/1"
                    },
                    "collection": [
                        {
                            "href": "http://localhost:8081/api/v1/surveys"
                        },
                        {
                            "href": "http://localhost:8081/api/v1/sessions/surveys/1"
                        }
                    ]
                }
            },...


# SESSIONS
### Create Session
| Method | Route | 
| ------------- | ------------- |
|POST           | /surveys | VOID

Setting the Session

    "user_id": 1,
    "survey_id": 3,
    "started_on": "13/12/2022 12:26:24",
    "closed_on": "14/12/2022 10:50:00",
    "access_status": 2

### FindBy SurveyID
| Method | Route | 
| ------------- | ------------- |
|GET            | /surveys/{ID} |

Find User with ID -- Return:

    {
    "session_id": 1,
    "userResponse": {
        "user_id": 1,
        "username": "Vini",
        "created_on": "12/12/2022 20:27:26",
        "_links": {
            "users": {
                "href": "http://localhost:8081/api/v1/users/1"
            }
        }
    },
    "surveyResponse": {
        "survey_id": 2,
        "user_id": 2,
        "userResponse": null,
        "description": "Shall we change the gutters?",
        "created_on": "12/12/2022 20:27:26",
        "_links": {
            "surveys": {
                "href": "http://localhost:8081/api/v1/surveys/2"
            }
        }
    },
    "survey_description": "Shall we change the gutters?",
    "started_on": "10/12/2022 18:22:30",
    "closed_on": "10/12/2022 18:40:30",
    "created_on": "12/12/2022 20:27:26",
    "amount_votes": 0,
    "up_votes": 0,
    "down_votes": 0,
    "access_status": "PRIVATE",
    "session_status": "FINALIZED",
    "allowedUserSession": [],
    "_links": {
        "self": {
            "href": "http://localhost:8081/api/v1/sessions/1"
        },
        "collection": {
            "href": "http://localhost:8081/api/v1/sessions"
        }
    }
}


    
### FindAll Surveys
| Method | Route | 
| ------------- | ------------- |
|GET            | /surveys |

Find all users registered users -- Return

    "_embedded": {
        "sessions": [
            {
                "session_id": 1,
                "userResponse": {
                    "user_id": 1,
                    "username": "Vini",
                    "created_on": "12/12/2022 20:27:26",
                    "_links": {
                        "users": {
                            "href": "http://localhost:8081/api/v1/users/1"
                        }
                    }
                },
                "surveyResponse": {
                    "survey_id": 2,
                    "user_id": 2,
                    "userResponse": null,
                    "description": "Shall we change the gutters?",
                    "created_on": "12/12/2022 20:27:26",
                    "_links": {
                        "surveys": {
                            "href": "http://localhost:8081/api/v1/surveys/2"
                        }
                    }
                },
                "survey_description": "Shall we change the gutters?",
                "started_on": "10/12/2022 18:22:30",
                "closed_on": "10/12/2022 18:40:30",
                "created_on": "12/12/2022 20:27:26",
                "amount_votes": 0,
                "up_votes": 0,
                "down_votes": 0,
                "access_status": "PRIVATE",
                "session_status": "FINALIZED",
                "allowedUserSession": [],
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/v1/sessions/1"
                    },
                    "collection": {
                        "href": "http://localhost:8081/api/v1/sessions"
                    }
                }
            },


### FindAll UserCanVote
| Method | Route | 
| ------------- | ------------- |
|GET            | /surveys/users/{ID} |

Finds the public and private sessions the user can vote in 

    ...
                "session_id": 3,
                "userResponse": {
                    "user_id": 1,
                    "username": "Vini",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "users": {
                            "href": "http://localhost:8081/api/v1/users/1"
                        }
                    }
                },
                "surveyResponse": {
                    "survey_id": 1,
                    "user_id": 1,
                    "userResponse": null,
                    "description": "Should we renovate the playground?",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "surveys": {
                            "href": "http://localhost:8081/api/v1/surveys/1"
                        }
                    }
                },
                "survey_description": "Should we renovate the playground?",
                "started_on": "13/12/2022 12:26:24",
                "closed_on": "13/12/2022 14:50:00",
                "created_on": "12/12/2022 20:38:24",
                "amount_votes": 0,
                "up_votes": 0,
                "down_votes": 0,
                "access_status": "PRIVATE",
                "session_status": "NONE",
                "allowedUserSession": [
                    {
                        "session_id": 3,
                        "user_id": 1
                    }
                ],
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/v1/sessions/3"
                    },
                    "collection": {
                        "href": "http://localhost:8081/api/v1/sessions"
                    }
                }
            },
            {
                "session_id": 4,
                "userResponse": {
                    "user_id": 1,
                    "username": "Vini",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "users": {
                            "href": "http://localhost:8081/api/v1/users/1"
                        }
                    }
                },
                "surveyResponse": {
                    "survey_id": 1,
                    "user_id": 1,
                    "userResponse": null,
                    "description": "Should we renovate the playground?",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "surveys": {
                            "href": "http://localhost:8081/api/v1/surveys/1"
                        }
                    }
                },
                "survey_description": "Should we renovate the playground?",
                "started_on": "13/12/2022 12:26:24",
                "closed_on": "13/12/2022 14:50:00",
                "created_on": "12/12/2022 20:38:30",
                "amount_votes": 0,
                "up_votes": 0,
                "down_votes": 0,
                "access_status": "PUBLIC",
                "session_status": "NONE",
                "allowedUserSession": [],
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/v1/sessions/4"
                    },
                    "collection": {
                        "href": "http://localhost:8081/api/v1/sessions"
                    }
                }
            }
        ]
    }

### FindAll SessionSurvey
| Method | Route | 
| ------------- | ------------- |
|GET            | /surveys/users/{ID} |

Find all sessions with this search

    ...

    "sessions": [
            {
                "session_id": 3,
                "userResponse": {
                    "user_id": 1,
                    "username": "Vini",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "users": {
                            "href": "http://localhost:8081/api/v1/users/1"
                        }
                    }
                },
                "surveyResponse": {
                    "survey_id": 1,
                    "user_id": 1,
                    "userResponse": null,
                    "description": "Should we renovate the playground?",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "surveys": {
                            "href": "http://localhost:8081/api/v1/surveys/1"
                        }
                    }
                },
                "survey_description": "Should we renovate the playground?",
                "started_on": "13/12/2022 12:26:24",
                "closed_on": "13/12/2022 14:50:00",
                "created_on": "12/12/2022 20:38:24",
                "amount_votes": 0,
                "up_votes": 0,
                "down_votes": 0,
                "access_status": "PRIVATE",
                "session_status": "NONE",
                "allowedUserSession": [
                    {
                        "session_id": 3,
                        "user_id": 1
                    }
                ],
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/v1/sessions/3"
                    },
                    "collection": {
                        "href": "http://localhost:8081/api/v1/sessions"
                    }
                }
            },
            {
                "session_id": 4,
                "userResponse": {
                    "user_id": 1,
                    "username": "Vini",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "users": {
                            "href": "http://localhost:8081/api/v1/users/1"
                        }
                    }
                },
                "surveyResponse": {
                    "survey_id": 1,
                    "user_id": 1,
                    "userResponse": null,
                    "description": "Should we renovate the playground?",
                    "created_on": "12/12/2022 20:36:33",
                    "_links": {
                        "surveys": {
                            "href": "http://localhost:8081/api/v1/surveys/1"
                        }
                    }
                },
                "survey_description": "Should we renovate the playground?",
                "started_on": "13/12/2022 12:26:24",
                "closed_on": "13/12/2022 14:50:00",
                "created_on": "12/12/2022 20:38:30",
                "amount_votes": 0,
                "up_votes": 0,
                "down_votes": 0,
                "access_status": "PUBLIC",
                "session_status": "NONE",
                "allowedUserSession": [],
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/v1/sessions/4"
                    },
                    "collection": {
                        "href": "http://localhost:8081/api/v1/sessions"
                    }
                }
            }
        ]
    } ...

# VOTE
| Method | Route | 
| ------------- | ------------- |
|POST           | /votes | VOID
    "user_id" : 1,
    "session_id" :3,
    "vote_status": 1
