Feature: Get Notes

  Scenario: Get all notes
    Given user have access of application
    When user wants to get all notes "/notes"
    Then validate get note status code 200
    And validate get note success message "Notes successfully retrieved"
