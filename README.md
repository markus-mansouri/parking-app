# **Parking Management App**

## **Objective**

The goal is to implement a **console-based application** using **object-oriented programming (OOP)** principles to
efficiently manage a parking area with multiple parking spots.

The application should allow:

- **Customer Registration**: Collecting their name and phone number and associating them with vehicles of specific
  types (e.g., car, motorcycle, truck).
- **Single Spot Reservation**: Each customer can only reserve one parking spot at a time, provided it is available.

### **Core Functionalities**

1. **Display all parking spots**.
2. **Reserve a parking spot for a customer**.
3. **Vacate an occupied parking spot** when a reservation is no longer needed.

### **Key Details**

The system's structure is clearly represented in the class diagram above. Here is a breakdown of key details:

- **Parking Spots**: Uniquely identified by their **spot number** and **area code** and can transition between vacant and occupied states.
- **Reservations**: Include details such as:
  - Start and end times.
  - The associated customer and vehicle.
  - The allocated parking spot.

### **Interface**

The application will use a **menu-driven console interface**, offering options for users to:

- Register customers.
- Manage reservations.
- View parking status.

Data management and persistence should use **Data Access Objects (DAOs)** to keep the code modular and organized,
adhering to OOP principles.

---

## **Hints**

### **1. Keep it Simple**

- Focus on building one functionality at a time.
- Start small and ensure everything works before adding complexity.
- Build incrementally for better understanding and maintainability.

### **2. Class Diagram**

![Class Diagram](img/Class_Diagram.png)

- Implement and Complete the entities in Java based on the class diagram.
- Ensure each class has appropriate attributes and methods to handle its responsibilities.

### **3. Implement Core Functionalities**

- Start with basic features:
  - Display all parking spots.
  - Register customers with their vehicles.
  - Reserve a parking spot for a customer.
- Gradually add advanced features:
  - Vacate a reserved parking spot.
  - Manage reservations (e.g., updating or extending reservations).

### **4. Data Persistence**

- Use **DAOs** to handle data storage and retrieval.
- Keep the implementation modular for easier maintenance.

### **5. Testing**

- Test each feature as you implement it to ensure correctness.
- Write **unit tests** for critical methods in DAOs and reservation handling.

---

## **Optional Tasks**

- Add new features
- Use **exception handling**
- Use **files or a database** for data persistence
- Use **GUI**

---

## **Resources and Further Reading**

For additional learning and insights, refer to the following:

### **SOLID Principles in Java**

- Learn about SOLID design principles and their application in Java:
  - [Baeldung: SOLID Principles](https://www.baeldung.com/solid-principles)
  - [Javatpoint: SOLID Principles in Java](https://www.javatpoint.com/solid-principles-java)

### **Design Patterns in Java**

- Explore practical Java examples for design patterns:
  - [TutorialsPoint: MVC Pattern](https://www.tutorialspoint.com/design_pattern/mvc_pattern.htm)
  - [TutorialsPoint: Singleton Pattern](https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm)
