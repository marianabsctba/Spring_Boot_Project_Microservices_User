import React, { useState, useEffect } from "react";
import { User } from "../types/user";
import { Box, Button, Grid, TextField } from "@mui/material";

interface UserFormProps {
    initialUserData?: User | null;
    onSubmit: (user: User) => void;
    onCancel: () => void;
}

const UserForm: React.FC<UserFormProps> = ({ initialUserData, onSubmit, onCancel }) => {
    const [name, setName] = useState<string>(initialUserData?.name || "");
    const [email, setEmail] = useState<string>(initialUserData?.email || "");

    useEffect(() => {
        if (initialUserData) {
            setName(initialUserData.name);
            setEmail(initialUserData.email);
        }
    }, [initialUserData]);

    const handleSubmit = () => {
        const userData: User = { id: initialUserData?.id || 0, name, email };
        onSubmit(userData);
    };

    return (
        <Box sx={{ padding: 3, borderRadius: 2, backgroundColor: "#f9f9f9" }}>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        variant="outlined"
                    />
                </Grid>

                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        label="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        variant="outlined"
                    />
                </Grid>

                <Grid item xs={12} display="flex" justifyContent="space-between">
                    <Button variant="contained" color="primary" onClick={handleSubmit}>
                        {initialUserData ? "Update User" : "Create User"}
                    </Button>
                    <Button variant="outlined" color="secondary" onClick={onCancel}>
                        Cancel
                    </Button>
                </Grid>
            </Grid>
        </Box>
    );
};

export default UserForm;
