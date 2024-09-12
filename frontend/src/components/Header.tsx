import React from "react";
import { AppBar, Toolbar, Typography, Box } from "@mui/material";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

const Header: React.FC = () => {
    return (
        <AppBar position="static">
            <Toolbar>
                <Box display="flex" flexGrow={1}>
                    <Typography variant="h6">
                        User Service
                    </Typography>
                </Box>
                <AccountCircleIcon fontSize="large" />
            </Toolbar>
        </AppBar>
    );
};

export default Header;
