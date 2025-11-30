describe("Login page", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173/login");
  });

  it("component renders", () => {
    cy.get('[data-testid="login-username-inp"]');
    cy.get('[data-testid="login-password-inp"]');
    cy.get('[data-testid="login-cancel-btn"]').click();
    cy.get('[data-testid="login-submit-btn"]');
  });

  it("Missing fields", () => {
    cy.get('[data-testid="login-username-inp"]').type("admin");
    cy.get('[data-testid="login-submit-btn"]').click();
    cy.get('[data-testid="login-password-inp-error"]').should(
      "contain",
      "password is required",
    );
    cy.get('[data-testid="login-username-inp"]').clear();

    cy.get('[data-testid="login-password-inp"]').type("admin");
    cy.get('[data-testid="login-submit-btn"]').click();
    cy.get('[data-testid="login-username-inp-error"]').should(
      "contain",
      "user name is required",
    );
  });

  it("Cancel Btn", () => {
    cy.get('[data-testid="login-username-inp"]').type("admin");
    cy.get('[data-testid="login-password-inp"]').type("tester1234");

    cy.get('[data-testid="login-cancel-btn"]').click();

    cy.get('[data-testid="login-password-inp"]').should("contain", "");
    cy.get('[data-testid="login-password-inp"]').should("contain", "");
  });

  it("Invalid login credentials", () => {
    cy.get('[data-testid="login-username-inp"]').type("admi");
    cy.get('[data-testid="login-password-inp"]').type("tester1234");
    cy.get('[data-testid="login-submit-btn"]').click();
    cy.get('[data-testid="error-notification"]');
  });

  it("login successfull", () => {
    cy.get('[data-testid="login-username-inp"]').type("admin");
    cy.get('[data-testid="login-password-inp"]').type("tester1234");
    cy.get('[data-testid="login-submit-btn"]').click();
    cy.get('[data-testid="success-notification"]');
    cy.url().should("contain", "dashboard");
  });
});
