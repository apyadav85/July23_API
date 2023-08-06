Feature: Update Notes

  Scenario: Update a note
    Given user have access of application
    When user wants to update a note "/notes"
      | noteid                   |
      | 64cc592cd1562c00f724d08b |
    Then validate update note status code 200
    And validate update note success message "Note successfully Updated"
