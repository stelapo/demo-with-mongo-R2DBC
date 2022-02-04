db = db.getSiblingDB("stelapodb");

db.createUser(
  {
    user: "stelapo",
    pwd: "stelapo",
    roles: [
      {
        role: "readWrite",
        db: "stelapodb"
      }
    ]
  }
)