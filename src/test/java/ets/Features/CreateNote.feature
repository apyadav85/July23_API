Feature: Create Notes

  Scenario Outline: Create a new note
    Given user have access of application
    When user wants to create a note "/notes"
      | title   | description   | category   |
      | <title> | <description> | <category> |
    Then validate create note status code 200
    And validate create note success message "Note successfully created"

    Examples: 
      | title    | description              | category |
      | Java     | Its a new course         | Work     |
      | Selenium | Its a UI automation tool | Work     |
      | Python   | Its a new era technology | Home     |
